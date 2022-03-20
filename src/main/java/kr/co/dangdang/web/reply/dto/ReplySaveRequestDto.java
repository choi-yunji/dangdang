package kr.co.dangdang.web.reply.dto;

import kr.co.dangdang.domain.entity.TbImageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class ReplySaveRequestDto {
    private String reply;
    private Long boardId;
    private String boardType;

    @Builder
    public ReplySaveRequestDto(
            String reply,
            Long boardId,
            String boardType
    ){
        this.reply = reply;
        this.boardId = boardId;
        this.boardType = boardType;
    }
}
