package kr.co.dangdang.domain.repository;

import kr.co.dangdang.domain.entity.TbUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbUserInfoRepository extends JpaRepository<TbUserInfo, String> {
    Optional<TbUserInfo> findByUserId(String userId);
}
