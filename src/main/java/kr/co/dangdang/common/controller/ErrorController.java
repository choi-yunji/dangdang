package kr.co.dangdang.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/internalerror")
    public void internalerror() {
        throw new RuntimeException("500 Internal Error !!");
    }
    @GetMapping("/forbidden")
    public void forbidden() {
        throw new RuntimeException("500 Internal Error !!");
    }
}
