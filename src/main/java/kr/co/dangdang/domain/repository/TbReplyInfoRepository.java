package kr.co.dangdang.domain.repository;

import kr.co.dangdang.domain.entity.TbReplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbReplyInfoRepository extends JpaRepository<TbReplyInfo, Long> {

    List<TbReplyInfo> findAllByBoardIdAndBoardType(Long boardId, String boardType);

    int countAllByBoardIdAndBoardType(Long boardId, String boardType);
}
