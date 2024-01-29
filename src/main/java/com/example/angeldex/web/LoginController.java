package com.example.angeldex.web;

import com.example.angeldex.model.dtos.LoginServiceModel;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import com.example.angeldex.security.CurrentUser;
import com.example.angeldex.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
    private final UserEntityService userEntityService;

    private final CurrentUser currentUser;

    public LoginController(UserEntityService userEntityService, CurrentUser currentUser) {
        this.userEntityService = userEntityService;
        this.currentUser = currentUser;
    }

    @GetMapping("/login")
    private String login(Model model) {
        if (!this.currentUser.isAnonymous()) {
            return "redirect:/home";
        } else {
            if (!model.containsAttribute("loginServiceModel")) {
                model.addAttribute("loginServiceModel", new LoginServiceModel());
                model.addAttribute("notFound", false);
            }
            if (!model.containsAttribute("searchArticlesDto")) {
                model.addAttribute("searchArticlesDto", new SearchArticlesDto());
            }

            return "login";
        }
    }

    @PostMapping("/login")
    private String onLogin(@Valid @ModelAttribute LoginServiceModel loginServiceModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginServiceModel", loginServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginServiceModel", bindingResult);
            return "redirect:/login";
        }
        if (this.userEntityService.authenticate(loginServiceModel.getEmail(), loginServiceModel.getPasswordHash())) {
            this.userEntityService.loginUser(loginServiceModel.getEmail());
            this.currentUser.setAnonymous(false);
            this.currentUser.setUsername(loginServiceModel.getEmail());
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("loginServiceModel", loginServiceModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    private String logout() {
        if (this.currentUser.isAnonymous()) {
            return "redirect:/login";
        } else {
            this.currentUser.setAnonymous(true);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            authentication.setAuthenticated(false);
            return "redirect:/";
        }
    }
}
