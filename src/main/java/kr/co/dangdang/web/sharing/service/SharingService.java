package kr.co.dangdang.web.sharing.service;

import kr.co.dangdang.domain.entity.TbImageInfo;
import kr.co.dangdang.domain.entity.TbSbInfo;
import kr.co.dangdang.domain.entity.TbUserInfo;
import kr.co.dangdang.domain.repository.TbImageInfoRepository;
import kr.co.dangdang.domain.repository.TbSbInfoTrpository;
import kr.co.dangdang.domain.repository.TbUserInfoRepository;
import kr.co.dangdang.web.sharing.dto.SharingFindResponseDto;
import kr.co.dangdang.web.sharing.dto.SharingSaveRequsetDto;
import kr.co.dangdang.web.dog.dto.DogFindResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class SharingService {
    private final TbSbInfoTrpository tbSbInfoRepository;
    private final TbImageInfoRepository tbImageInfoRepository;
    private final TbUserInfoRepository tbUserInfoRepository;

    public Long saveContent(SharingSaveRequsetDto sharingSaveRequsetDto) {

        TbSbInfo tbSbInfo = tbSbInfoRepository.save(TbSbInfo.builder()
                .sbContents(sharingSaveRequsetDto.getContent())
                .sbDeleteYn("N")
                .build());

        return tbSbInfo.getSbId();
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
                        .boardType("S")
                        .imageFileNm(fileNm)
                        .imageOriginalNm(originalNm)
                        .imagePath(imagePath)
                        .build()
        );

//        }
    }
    public List<SharingFindResponseDto> loadShraingBoard(Pageable pageable){
        List<TbSbInfo> top5 = tbSbInfoRepository.findAllBySbDeleteYnOrderBySbIdDesc("N", pageable);
        List<SharingFindResponseDto> retDto = new ArrayList<>();
        for (TbSbInfo item : top5) {
            List<TbImageInfo> imageInfos = tbImageInfoRepository.findAllByBoardIdAndBoardType(item.getSbId(), "S");
            TbUserInfo userInfo = tbUserInfoRepository.findByUserId(item.getCreId()).orElse(null);
            retDto.add(
                    SharingFindResponseDto.builder()
                            .sbId(item.getSbId())
                            .sbContents(item.getSbContents())
                            .sbDeleteYn(item.getSbDeleteYn())
                            .modDt(item.getModDt())
                            .userId(userInfo.getUserId())
                            .userNickName(userInfo.getUserNickName())
                            .images(imageInfos)
                            .build()
            );
        }
        return retDto;
    }


}