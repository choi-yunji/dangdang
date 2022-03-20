package kr.co.dangdang.web.reply.controller;

import kr.co.dangdang.common.annotation.LoginUser;
import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.domain.entity.TbReplyInfo;
import kr.co.dangdang.web.reply.dto.ReplyResponseDto;
import kr.co.dangdang.web.reply.dto.ReplySaveRequestDto;
import kr.co.dangdang.web.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {

    private final ReplyService replyService;

    @GetMapping("/api/reply/findAllReply/{boardType}/{boardId}")
    public List<Map<String, Object>> findAllReply(@PathVariable String boardType, @PathVariable Long boardId) {
        return replyService.findAllReplyByBoardIdAndBoardType(boardId, boardType);
    }

    @GetMapping("/api/reply/countReply/{boardType}/{boardId}")
    public int countReply(@PathVariable String boardType, @PathVariable Long boardId) {
        return replyService.countReply(boardId, boardType);
    }

    @PostMapping("/api/reply/save")
    public Long saveReply(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        System.out.println(replySaveRequestDto.toString());
        return replyService.saveReply(replySaveRequestDto);
    }


}
