package kr.co.dangdang.web.dog.service;

import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.domain.entity.TbDbInfo;
import kr.co.dangdang.domain.entity.TbImageInfo;
import kr.co.dangdang.domain.entity.TbUserInfo;
import kr.co.dangdang.domain.repository.TbDbInfoRepository;
import kr.co.dangdang.domain.repository.TbImageInfoRepository;
import kr.co.dangdang.domain.repository.TbLikeInfoRepository;
import kr.co.dangdang.domain.repository.TbUserInfoRepository;
import kr.co.dangdang.web.dog.dto.DogFindResponseDto;
import kr.co.dangdang.web.dog.dto.DogSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class DogService {

    private final TbDbInfoRepository tbDbInfoRepository;
    private final TbImageInfoRepository tbImageInfoRepository;
    private final TbUserInfoRepository tbUserInfoRepository;
    private final TbLikeInfoRepository tbLikeInfoRepository;

    public Long saveContent(DogSaveRequestDto dogSaveRequestDto) {

        TbDbInfo tbDbInfo = tbDbInfoRepository.save(TbDbInfo.builder()
                            .dogContents(dogSaveRequestDto.getContent())
                            .dogDeleteYn("N")
                            .build());

        return tbDbInfo.getDogId();
    }

    public void saveImage(MultipartFile multipartFile, Long boardId) {
        String imagePath = "C:\\upload";

//        for (MultipartFile file : multipartFile) {
        String originalNm = multipartFile.getOriginalFilename();


        String fe = "";
        final Pattern PATTERN = Pattern.compile("(.*)\\.(.*)");
        Matcher m = PATTERN.matcher(originalNm);
        if (m.find()) {
            fe = m.group(2);
        }
        System.out.println("File extension is : "+fe);
        UUID uid = UUID.randomUUID();
        String fileNm = uid.toString() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) +"."+ fe;

            File saveFile = new File(imagePath, fileNm);
            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            tbImageInfoRepository.save(
                    TbImageInfo.builder()
                            .boardId(boardId)
                            .boardType("D")
                            .imageFileNm(fileNm)
                            .imageOriginalNm(originalNm)
                            .imagePath(imagePath)
                            .build()
            );

//        }
    }
    public List<DogFindResponseDto> loadDogBoard(Pageable pageable,SessionUser sessionUser){
        List<TbDbInfo> top5 = tbDbInfoRepository.findAllByDogDeleteYnOrderByDogIdDesc("N", pageable);
        List<DogFindResponseDto> retDto = new ArrayList<>();
        for (TbDbInfo item : top5) {
            List<TbImageInfo> imageInfos = tbImageInfoRepository.findAllByBoardIdAndBoardType(item.getDogId(), "D");
            TbUserInfo userInfo = tbUserInfoRepository.findByUserId(item.getCreId()).orElse(null);
            retDto.add(
                    DogFindResponseDto.builder()
                            .dogId(item.getDogId())
                            .dogContents(item.getDogContents())
                            .dogDeleteYn(item.getDogDeleteYn())
                            .modDt(item.getModDt())
                            .userId(userInfo.getUserId())
                            .userNickName(userInfo.getUserNickName())
                            .images(imageInfos)
                            .likeYn(tbLikeInfoRepository.findByBoardIdAndBoardTypeAndCreId(item.getDogId(), "D", sessionUser.getUserId()).isPresent())
                            .likeCount(tbLikeInfoRepository.countAllByBoardIdAndBoardType(item.getDogId(), "D"))
                            .build()
            );
        }
        return retDto;
    }



}

