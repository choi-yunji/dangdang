package kr.co.dangdang.web.encyclopedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Encyclopedia {
    @GetMapping("/encyclopedia")
    public String getEncyclopediaBoard(){return "view/encyclopedia/encyclopedia";}

    @GetMapping("/encyclopedia/write")
    public String getEncyclopediaWrite(){return "view/encyclopedia/summernote";}

}
