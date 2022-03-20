package kr.co.dangdang.common.dto;

import kr.co.dangdang.domain.entity.TbUserInfo;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class SessionUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private String userPw;
    private String userNickName;
    private LocalDateTime userRegDt;


    public SessionUser(TbUserInfo tbUserInfo) {
        this.userId = tbUserInfo.getUserId();
        this.userName = tbUserInfo.getUsername();
        this.userPw = tbUserInfo.getUserPw();
        this.userNickName = tbUserInfo.getUserNickName();
        this.userRegDt = tbUserInfo.getUserRegDt();
    }
}