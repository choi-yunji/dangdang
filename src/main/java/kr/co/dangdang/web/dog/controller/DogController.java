package kr.co.dangdang.web.dog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DogController {

    @GetMapping("/dog")
    public String getDogBoard(){return "view/dog/dog";}

    @GetMapping("/dog/write")
    public String getDogWrite(){return "view/dog/write";}

    @GetMapping("/dog/detail")
    public String getDogDetail(){return "view/dog/dogDetail";}

}
