package kr.co.dangdang.web.dog.dto;

import kr.co.dangdang.domain.entity.TbImageInfo;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DogFindResponseDto {
    private Long dogId;
    private String dogContents;
    private String dogDeleteYn;
    private String userId;
    private String userNickName;
    private LocalDateTime modDt;

    private List<TbImageInfo>images;

    @Builder
    public DogFindResponseDto(
            Long dogId,
            String dogContents,
            String dogDeleteYn,
            String userId,
            String userNickName,
            LocalDateTime modDt,
            List<TbImageInfo>images
    ){
        this.dogId = dogId;
        this.dogContents = dogContents;
        this.dogDeleteYn = dogDeleteYn;
        this.userId = userId;
        this.userNickName = userNickName;
        this.modDt = modDt;
        this.images = images;

    }
}
