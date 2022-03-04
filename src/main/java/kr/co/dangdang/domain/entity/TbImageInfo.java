package kr.co.dangdang.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_IMAGE_INFO")
@Entity
public class TbImageInfo {
    @Id
    @Column(name = "IMAGE_ID", length = 20, nullable = false)
    private String imageId;

    @Column(name = "BOARD_ID", length = 20, nullable = false)
    private String boardId;

    @Column(name = "BOARD_TYPE", length = 20, nullable = false)
    private String boardType;

    @Column(name = "IMAGE_FILENM", length = 100, nullable = false)
    private String imageFilenm;

    @Column(name = "IMAGE_PATH", length = 500, nullable = false)
    private String imagePath;

    @Column(name = "IMAGE_WIRTER", length = 1000, nullable = false)
    private String imageWirter;

    @Column(name = "IMAGE_REG_DT",  nullable = false)
    private LocalDateTime imageRegDt;
}
