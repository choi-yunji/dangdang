package kr.co.dangdang.domain.repository;

import kr.co.dangdang.domain.entity.TbSbInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbSbInfoTrpository extends JpaRepository<TbSbInfo, Long> {
    List<TbSbInfo> findAllBySbDeleteYnOrderBySbIdDesc(String deleteYn, Pageable pageable);
}
