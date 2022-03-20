package kr.co.dangdang.web.mypage.controller;

import kr.co.dangdang.common.annotation.LoginUser;
import kr.co.dangdang.common.dto.SessionUser;
import kr.co.dangdang.web.mypage.service.MyPageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MyPageApiController {

    private final MyPageService myPageService;

    @PostMapping("/api/my/save/image")
    public boolean write(MultipartFile uploadFile, @LoginUser SessionUser sessionUser){
        Long imageId = myPageService.saveImage(uploadFile, 0L);
        myPageService.saveProfileImg(imageId, sessionUser);
        return true;
    }

    @GetMapping("/api/my/getUserProfileImg")
    public Long getUserProfileImg(@LoginUser SessionUser sessionUser) {

        return myPageService.getUserProfileImg(sessionUser.getUserId());
    }
}
