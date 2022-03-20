package kr.co.dangdang.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_REPLY_INFO")
@Entity
public class TbReplyInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_ID", length = 20, nullable = false)
    private Long replyId;

    @Column(name = "REPLY", columnDefinition = "TEXT", nullable = false)
    private String reply;

    @Column(name = "BOARD_ID", length = 20, nullable = false)
    private Long boardId;

    @Column(name = "BOARD_TYPE", length = 1, nullable = false)
    private String boardType;

    @Column(name = "REPLY_DELETE_YN", length = 1, nullable = false)
    private String replyDeleteYn;

    @Builder
    public TbReplyInfo(
         String reply,
         Long boardId,
         String boardType,
         String replyDeleteYn
    ){
        this.reply = reply;
        this.boardId = boardId;
        this.boardType = boardType;
        this.replyDeleteYn = replyDeleteYn;
    }


}
