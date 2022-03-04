package kr.co.dangdang.web.join.controller;

import kr.co.dangdang.domain.entity.TbUserInfo;
import kr.co.dangdang.domain.repository.TbUserInfoRepository;
import kr.co.dangdang.web.join.dto.JoinSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class JoinService {
    private final TbUserInfoRepository tbUserInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public Boolean cheackIsIdExists(String id) { return tbUserInfoRepository.findByUserId(id).isPresent();}

    public void saveUser(JoinSaveRequestDto joinSaveRequestDto){
        TbUserInfo tbUserInfo = TbUserInfo.builder()
                .userId(joinSaveRequestDto.getUserId())
                .userPw(passwordEncoder.encode(joinSaveRequestDto.getUserPw()))
                .userName(joinSaveRequestDto.getUserName())
                .userNickName(joinSaveRequestDto.getUserNickName())
                .userRegDt(LocalDateTime.now())
                .build();
        tbUserInfoRepository.save(tbUserInfo);

    }
}
