package kr.co.dangdang.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_IMAGE_INFO")
@Entity
public class TbImageInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID", length = 20, nullable = false)
    private Long imageId;

    @Column(name = "BOARD_ID", length = 20, nullable = false)
    private Long boardId;

    @Column(name = "BOARD_TYPE", length = 1, nullable = false)
    private String boardType;

    @Column(name = "IMAGE_FILE_NM", length = 100, nullable = false)
    private String imageFileNm;

    @Column(name = "IMAGE_ORIGINAL_NM", length = 100, nullable = false)
    private String imageOriginalNm;

    @Column(name = "IMAGE_PATH", length = 500, nullable = false)
    private String imagePath;


    @Builder
    public TbImageInfo(
            Long boardId,
            String boardType,
            String imageFileNm,
            String imageOriginalNm,
            String imagePath
    ){
        this.boardId = boardId;
        this.boardType = boardType;
        this.imageFileNm = imageFileNm;
        this.imageOriginalNm = imageOriginalNm;
        this.imagePath = imagePath;
    }
}
