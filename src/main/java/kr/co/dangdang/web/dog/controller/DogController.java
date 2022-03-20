package kr.co.dangdang.web.dog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DogController {

    @GetMapping("/dog")
    public String getDogBoard(){return "view/dog/dog";}

    @GetMapping("/dog/write")
    public String getDogWrite(){return "view/dog/write";}


    @GetMapping("/dog/detail/{dogId}/{imageId}")
    public String getDogDetail(@PathVariable Long dogId, @PathVariable Long imageId, Model model) {
        System.out.println(dogId);
        model.addAttribute("dogId", dogId);
        model.addAttribute("imageId", imageId);
        return "view/dog/dogDetail";
    }

}
