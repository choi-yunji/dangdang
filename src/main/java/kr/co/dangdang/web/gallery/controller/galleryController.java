package kr.co.dangdang.web.gallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class galleryController {
    @GetMapping("/gallery")
    public String getGallery(){return "view/gallery/gallery";}
}
