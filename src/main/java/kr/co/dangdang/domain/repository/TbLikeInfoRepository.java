package kr.co.dangdang.domain.repository;

import kr.co.dangdang.domain.entity.TbLikeInfo;
import kr.co.dangdang.domain.entity.TbUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbLikeInfoRepository extends JpaRepository<TbLikeInfo, Long> {
    Optional<TbLikeInfo> findByBoardIdAndBoardTypeAndCreId(Long boardId, String boardType, String userId);

    int countAllByBoardIdAndBoardType(Long boardId, String boardType);
}
