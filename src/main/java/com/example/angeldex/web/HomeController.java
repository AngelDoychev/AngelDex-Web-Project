package com.example.angeldex.web;

import com.example.angeldex.model.dtos.EmailRegisterDto;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import com.example.angeldex.security.CurrentUser;
import com.example.angeldex.service.ArticleService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
private final CurrentUser currentUser;
private final ArticleService articleService;

    public HomeController(CurrentUser currentUser, ArticleService articleService) {
        this.currentUser = currentUser;
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String reHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, OAuth2Authentication auth) {
        if (!model.containsAttribute("emailRegisterDto")) {
            model.addAttribute("emailRegisterDto", new EmailRegisterDto());
        }
        if (!model.containsAttribute("searchArticlesDto")) {
            model.addAttribute("searchArticlesDto", new SearchArticlesDto());
        }

        if (null != auth && auth.getUserAuthentication().isAuthenticated()) {
            this.currentUser.setUsername(auth.getName());
            this.currentUser.setAnonymous(false);
        }

        return "index";
    }

}
