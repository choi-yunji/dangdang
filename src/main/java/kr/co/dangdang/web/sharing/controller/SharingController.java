package kr.co.dangdang.web.sharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SharingController {

    @GetMapping("/sharing")
    public String getSharingBoard(){return "view/sharing/sharing";}

    @GetMapping("/sharing/write")
    public String getSharingWrite(){return "view/sharing/sharingWrite";}

    @GetMapping("/sharing/detail/{sbId}/{imageId}")
    public String getSharingDetail(@PathVariable Long sbId, @PathVariable Long imageId, Model model) {
        System.out.println(sbId);
        model.addAttribute("sbId", sbId);
        model.addAttribute("imageId", imageId);
        return "view/sharing/sharingDetail";
    }
}
