package com.example.angeldex.web;

import com.example.angeldex.model.dtos.EmailRegisterDto;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TeamController {



    @GetMapping("/team")
    public String team(Model model) {
        if (!model.containsAttribute("emailRegisterDto")) {
            model.addAttribute("emailRegisterDto", new EmailRegisterDto());
        }
        if (!model.containsAttribute("searchArticlesDto")) {
            model.addAttribute("searchArticlesDto", new SearchArticlesDto());
        }
        return "team";
    }

}
