package kr.co.dangdang.web.like.service;

import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.domain.entity.TbLikeInfo;
import kr.co.dangdang.domain.repository.TbLikeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final TbLikeInfoRepository tbLikeInfoRepository;


    public Long saveFootPrint(Long sharingId, String boardType){
        return tbLikeInfoRepository.save(
                TbLikeInfo.builder()
                        .boardId(sharingId)
                        .boardType(boardType)
                        .build()
        ).getLikeId();
    }

    public boolean checkFootPrint(Long sharingId, SessionUser sessionUser, String boardType){
        return tbLikeInfoRepository.findByBoardIdAndBoardTypeAndCreId(sharingId, boardType, sessionUser.getUserId()).isPresent();
    }

    public void deleteFootPrint(Long sharingId, SessionUser sessionUser, String boardType){
        tbLikeInfoRepository.delete(
                tbLikeInfoRepository.findByBoardIdAndBoardTypeAndCreId(sharingId, boardType, sessionUser.getUserId()).orElse(null)
        );

    }

    public int countFootPrint(Long sharingId, String boardType){
        return tbLikeInfoRepository.countAllByBoardIdAndBoardType(sharingId, boardType);
    }
}
