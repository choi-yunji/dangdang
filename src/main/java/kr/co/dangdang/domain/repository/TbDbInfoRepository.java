package kr.co.dangdang.domain.repository;

import kr.co.dangdang.domain.entity.TbDbInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbDbInfoRepository extends JpaRepository<TbDbInfo,Long> {
    List<TbDbInfo> findAllByDogDeleteYnOrderByDogIdDesc(String deleteYn, Pageable pageable);

}
