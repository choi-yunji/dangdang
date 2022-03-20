package kr.co.dangdang.common.service;

import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.domain.entity.TbUserInfo;
import kr.co.dangdang.domain.repository.TbUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final TbUserInfoRepository tbUserInfoRepository;
    private final HttpSession httpSession;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUserInfo userInfo = tbUserInfoRepository.findByUserId(username).orElseThrow(()-> new UsernameNotFoundException(username));


        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getKey()));
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
//
        httpSession.setAttribute("user", new SessionUser(userInfo));

        return new User(userInfo.getUserId(), userInfo.getUserPw(), grantedAuthorities);
    }
}
