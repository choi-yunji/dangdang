package kr.co.dangdang.web.reply.service;


import kr.co.dangdang.domain.entity.TbReplyInfo;
import kr.co.dangdang.domain.nativeRepository.TbReplyInfoNativeRepository;
import kr.co.dangdang.domain.repository.TbReplyInfoRepository;
import kr.co.dangdang.web.reply.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final TbReplyInfoRepository tbReplyInfoRepository;
    private final TbReplyInfoNativeRepository tbReplyInfoNativeRepository;

    public List<Map<String, Object>> findAllReplyByBoardIdAndBoardType(Long boardId, String boardType) {
        return tbReplyInfoNativeRepository.findAllByBoardIdAndBoardType(boardId,boardType);
    }

    public int countReply(Long boardId, String boardType) {
        return tbReplyInfoRepository.countAllByBoardIdAndBoardType(boardId, boardType);
    }

    public Long saveReply(ReplySaveRequestDto replySaveRequestDto) {
        //나중에 있으면 업데이트 없으면 세이브로 변경

        return tbReplyInfoRepository.save(
                TbReplyInfo.builder()
                        .reply(replySaveRequestDto.getReply())
                        .boardId(replySaveRequestDto.getBoardId())
                        .boardType(replySaveRequestDto.getBoardType())
                        .replyDeleteYn("Y")
                        .build()
        ).getReplyId();
    }
}
