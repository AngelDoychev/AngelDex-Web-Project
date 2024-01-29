package com.example.angeldex.web;

import com.example.angeldex.model.dtos.EmailRegisterDto;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.model.enums.RoleNameEnum;
import com.example.angeldex.security.CurrentUser;
import com.example.angeldex.service.ArticleService;
import com.example.angeldex.service.RoleService;
import com.example.angeldex.service.UserEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    private final UserEntityService userEntityService;
    private final CurrentUser currentUser;
    private final ArticleService articleService;
    private final RoleService roleService;



    public ProfileController(UserEntityService userEntityService, CurrentUser currentUser, ArticleService articleService, RoleService roleService) {
        this.userEntityService = userEntityService;
        this.currentUser = currentUser;
        this.articleService = articleService;
        this.roleService = roleService;
    }

    @GetMapping("/profile/{id}")
    public String allArticlesByUser(@PathVariable String id, Model model) {
        if (this.currentUser.isAnonymous()) {
            return "redirect:/login";
        }
        UserEntity user = this.userEntityService.findByEmail(this.currentUser.getUsername());
        if (user.getRoles().stream().anyMatch(role -> role.equals(this.roleService.findRoleByRoleName(RoleNameEnum.ADMIN)))) {
            model.addAttribute("isAdmin", true);
        }else {
            model.addAttribute("isAdmin", false);
        }
        if (!model.containsAttribute("emailRegisterDto")) {
            model.addAttribute("emailRegisterDto", new EmailRegisterDto());
        }
        if (!model.containsAttribute("articlesByUser")) {
            model.addAttribute("articlesByUser", this.articleService.findAllByUserId(Long.parseLong(id)));
        }
        if (!model.containsAttribute("searchArticlesDto")) {
            model.addAttribute("searchArticlesDto", new SearchArticlesDto());
        }
        return "profile";
    }


}
