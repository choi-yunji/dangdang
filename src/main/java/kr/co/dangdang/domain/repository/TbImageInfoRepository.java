package kr.co.dangdang.domain.repository;

import kr.co.dangdang.domain.entity.TbImageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbImageInfoRepository extends JpaRepository<TbImageInfo,Long> {

    List<TbImageInfo> findAllByBoardIdAndBoardType(Long boardId, String boardType);

    TbImageInfo findByImageId(Long imageId);
}

