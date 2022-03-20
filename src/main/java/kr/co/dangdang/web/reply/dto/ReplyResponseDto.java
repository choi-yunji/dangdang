package kr.co.dangdang.web.reply.dto;

import kr.co.dangdang.domain.entity.TbImageInfo;
import kr.co.dangdang.domain.entity.TbReplyInfo;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReplyResponseDto {
    private Long replyId;
    private String reply;
    private Long boardId;
    private Long profileImgId;
    private String userId;
    private String userNickNm;

    @Builder
    public ReplyResponseDto(
            String reply,
            String userId,
            String userNickNm,
            Long boardId,
            Long profileImgId
    ) {
        this.reply = reply;
        this.boardId = boardId;
        this.profileImgId = profileImgId;
        this.userId = userId;
        this.userNickNm = userNickNm;
    }
}
