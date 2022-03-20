package kr.co.dangdang.web.reply.dto;

import kr.co.dangdang.domain.entity.TbImageInfo;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReplyRequestDto {
    private Long replyId;
    private String reply;
    private Long boardId;
    private String boardType;
    private String userId;
    private String userNickName;
    private LocalDateTime modDt;

    private List<TbImageInfo>images;

    @Builder
    public ReplyRequestDto(
            Long replyId,
            String reply,
            Long boardId,
            String boardType,
            String userId,
            String userNickName,
            LocalDateTime modDt,
            List<TbImageInfo> images
    ){
        this.replyId = replyId;
        this.reply = reply;
        this.boardId = boardId;
        this.boardType = boardType;
        this.userId = userId;
        this.userNickName = userNickName;
        this.modDt = modDt;
        this.images = images;

    }
}
