package kr.co.dangdang.web.join.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JoinSaveRequestDto {
    private String userId;
    private String userPw;
    private String userName;
    private String userNickName;

}
