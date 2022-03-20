package kr.co.dangdang.web.mypage.service;

import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.domain.entity.TbImageInfo;
import kr.co.dangdang.domain.entity.TbUserInfo;
import kr.co.dangdang.domain.repository.TbImageInfoRepository;
import kr.co.dangdang.domain.repository.TbUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final TbImageInfoRepository tbImageInfoRepository;
    private final TbUserInfoRepository tbUserInfoRepository;


    public Long saveImage(MultipartFile multipartFile, Long boardId) {
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

        return tbImageInfoRepository.save(
                TbImageInfo.builder()
                        .boardId(boardId)
                        .boardType("P")
                        .imageFileNm(fileNm)
                        .imageOriginalNm(originalNm)
                        .imagePath(imagePath)
                        .build()
        ).getImageId();

//        }
    }

    @Transactional
    public void saveProfileImg(Long imgId, SessionUser sessionUser) {
        TbUserInfo tbUserInfo = tbUserInfoRepository.findByUserId(sessionUser.getUserId()).orElse(null);
        tbUserInfo.updateProfileImg(imgId);
    }

    public Long getUserProfileImg(String  userId) {
        TbUserInfo tbUserInfo = tbUserInfoRepository.findByUserId(userId).orElse(null);
        return tbUserInfo.getImageId();
    }
}
