package kr.co.dangdang.web.sharing.dto;

import kr.co.dangdang.domain.entity.TbImageInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SharingFindResponseDto {
    private Long sbId;
    private String sbContents;
    private String sbDeleteYn;
    private String userId;
    private String userNickName;
    private LocalDateTime modDt;

    private List<TbImageInfo> images;

    @Builder
    public SharingFindResponseDto(
            Long sbId,
            String sbContents,
            String sbDeleteYn,
            String userId,
            String userNickName,
            LocalDateTime modDt,
            List<TbImageInfo>images
    ){
        this.sbId = sbId;
        this.sbContents = sbContents;
        this.sbDeleteYn = sbDeleteYn;
        this.userId = userId;
        this.userNickName = userNickName;
        this.modDt = modDt;
        this.images = images;

    }
}
