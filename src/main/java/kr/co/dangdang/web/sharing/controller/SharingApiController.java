package kr.co.dangdang.web.sharing.controller;

import kr.co.dangdang.common.annotation.LoginUser;
import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.domain.entity.TbImageInfo;
import kr.co.dangdang.domain.repository.TbImageInfoRepository;
import kr.co.dangdang.web.dog.dto.DogFindResponseDto;
import kr.co.dangdang.web.like.service.LikeService;
import kr.co.dangdang.web.sharing.dto.SharingFindResponseDto;
import kr.co.dangdang.web.sharing.dto.SharingSaveRequsetDto;
import kr.co.dangdang.web.sharing.service.SharingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@RestController
public class SharingApiController {
    private final SharingService sharingService;
    private final LikeService likeService;
    private final TbImageInfoRepository tbImageInfoRepository;

    @PostMapping("/api/sharing/save/sharing")
    public Long write(@RequestBody SharingSaveRequsetDto sharingSaveRequsetDto){
        return sharingService.saveContent(sharingSaveRequsetDto);
    }

    @PostMapping("/api/sharing/save/image/{boardId}")
    public boolean write(MultipartFile uploadFile, @PathVariable Long boardId){
        sharingService.saveImage(uploadFile, boardId);
        return true;
    }

    @PostMapping("/api/sharing/makeFootPrint/{sharingId}")
    public Long makeFootPrint(@PathVariable Long sharingId, @LoginUser SessionUser sessionUser){
        Long retLong = 0L;
        if (likeService.checkFootPrint(sharingId, sessionUser, "S")) {
            likeService.deleteFootPrint(sharingId, sessionUser, "S");
        }else{
            retLong = likeService.saveFootPrint(sharingId, "S");
        }
        return retLong;
    }


    @GetMapping("/api/sharing/loadShraingBoard/{pageId}")
    public List<SharingFindResponseDto> loadShraingBoard(@PathVariable int pageId, @LoginUser SessionUser sessionUser){
        Pageable page = PageRequest.of(pageId, 3, Sort.by("sbId"));
        return sharingService.loadShraingBoard(page, sessionUser);
    }

    @GetMapping("/api/sharing/countFootPrint/{sharingId}")
    public int countFootPrint(@PathVariable Long sharingId){
        return likeService.countFootPrint(sharingId, "S");
    }


    @GetMapping("/api/sharing/getImage/{imageId}")
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

//   @ResponseBody
//    @RequestMapping(value = "/ck/fileupload", method = {RequestMethod.POST, RequestMethod.GET})
//    public String fileUpload() {
//        //사진 저장 하는 코드
//       sharingService.saveImage(null, 0);
//
//
//
//
//        //System.out.println("{ \"uploaded\" : true, \"url\" : \"http://localhost:8080/yourhome/upload/" + fileNameList.get(0) + "\" }");
//        //return "{ \"uploaded\" : true, \"url\" : \"C:\\upload" + fileNameList.get(0) + "\" }";
//    }
}
