package kr.co.dangdang.web.dog.controller;

import kr.co.dangdang.common.annotation.LoginUser;
import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.domain.entity.TbDbInfo;
import kr.co.dangdang.domain.entity.TbImageInfo;
import kr.co.dangdang.domain.repository.TbImageInfoRepository;
import kr.co.dangdang.web.dog.dto.DogFindResponseDto;
import kr.co.dangdang.web.dog.dto.DogSaveRequestDto;
import kr.co.dangdang.web.dog.service.DogService;
import kr.co.dangdang.web.join.dto.JoinSaveRequestDto;
import kr.co.dangdang.web.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DogApiController {

    private final DogService dogService;
    private final LikeService likeService;
    private final TbImageInfoRepository tbImageInfoRepository;

    @PostMapping("/api/dog/save/dog")
    public Long write(@RequestBody DogSaveRequestDto dogSaveRequestDto){
        return dogService.saveContent(dogSaveRequestDto);
    }

    @PostMapping("/api/dog/save/image/{boardId}")
    public boolean write(MultipartFile uploadFile, @PathVariable Long boardId){
        dogService.saveImage(uploadFile, boardId);
        return true;
    }

    @GetMapping("/api/dog/loadDogBoard/{pageId}")
    public List<DogFindResponseDto> loadDogBoard(@PathVariable int pageId, @LoginUser SessionUser sessionUser){
        Pageable page = PageRequest.of(pageId, 5, Sort.by("dogId"));
        return dogService.loadDogBoard(page, sessionUser);
    }

    @PostMapping("/api/dog/makeFootPrint/{dogId}")
    public Long makeFootPrint(@PathVariable Long dogId, @LoginUser SessionUser sessionUser){
        Long retLong = 0L;
        if (likeService.checkFootPrint(dogId, sessionUser, "D")) {
            likeService.deleteFootPrint(dogId, sessionUser, "D");
        }else{
            retLong = likeService.saveFootPrint(dogId, "D");
        }
        return retLong;
    }

    @GetMapping("/api/dog/getImage/{imageId}")
    public void imageSearch(@PathVariable Long imageId, HttpServletResponse response) throws IOException {
        TbImageInfo tbImageInfo = tbImageInfoRepository.findByImageId(imageId);
        response.setContentType("image/gif");
        ServletOutputStream bout = response.getOutputStream();
        String imgPath = tbImageInfo.getImagePath() + File.separator + tbImageInfo.getImageFileNm();

        FileInputStream f = new FileInputStream((imgPath));
        int length ;
        byte[] buffer = new byte[10];
        while ((length = f.read(buffer)) != -1) {
            bout.write(buffer, 0, length);
        }

    }

}
