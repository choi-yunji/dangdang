package kr.co.dangdang.web.join.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {
    @GetMapping("/join")
    public String getJoinPage(){return "view/join/join";}
}
