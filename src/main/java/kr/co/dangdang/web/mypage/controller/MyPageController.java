package kr.co.dangdang.web.mypage.controller;

import kr.co.dangdang.common.annotation.LoginUser;
import kr.co.dangdang.common.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    @GetMapping("/myPage")
    public String getMyPage() {
        return "view/myPage/my";
    }
}
