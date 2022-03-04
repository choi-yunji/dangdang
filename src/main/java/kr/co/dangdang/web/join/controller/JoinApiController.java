package kr.co.dangdang.web.join.controller;

import kr.co.dangdang.web.join.dto.JoinSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JoinApiController {

    private final JoinService joinService;
    @PostMapping("/api/join/checkIsIdExists/{id}")
    public Boolean checkIsIdExists(@PathVariable String id) {
        return joinService.cheackIsIdExists(id);
    }

    @PostMapping("/api/join/join")
    public void join(@RequestBody JoinSaveRequestDto joinSaveRequestDto){
        System.out.println(joinSaveRequestDto.toString());
        joinService.saveUser(joinSaveRequestDto);
    }

}
