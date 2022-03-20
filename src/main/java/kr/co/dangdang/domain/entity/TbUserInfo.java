package kr.co.dangdang.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_USER_INFO")
@Entity
public class TbUserInfo implements UserDetails {
    @Id
    @Column(name = "USER_ID", length = 50, nullable = false)
    private String userId;

    @Column(name = "USER_Name", length = 20, nullable = false)
    private String userName;

    @Column(name = "USER_PW", length = 255, nullable = false)
    private String userPw;

    @Column(name = "USER_NICKNAME", length = 100, nullable = false)
    private String userNickName;

    @Column(name = "USER_REG_DT",  nullable = false)
    private LocalDateTime userRegDt;

    @Column(name = "IMAGE_ID", length = 20)
    private Long imageId;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Builder
    public TbUserInfo(
            String userId,
            String userPw,
            String userName,
            String userNickName,
            LocalDateTime userRegDt
    ){
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userNickName = userNickName;
        this.userRegDt = userRegDt;
    }

    public void updateProfileImg(Long imageId) {
        this.imageId = imageId;
    }


}
