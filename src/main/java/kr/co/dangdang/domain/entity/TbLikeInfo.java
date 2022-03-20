package kr.co.dangdang.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_LIKE_INFO")
@Entity
public class TbLikeInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID", length = 20, nullable = false)
    private Long likeId;

    @Column(name = "BOARD_ID", length = 20, nullable = false)
    private String boardId;

    @Column(name = "BOARD_TYPE", length = 1, nullable = false)
    private String boardType;
}
