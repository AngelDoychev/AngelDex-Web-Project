package com.example.angeldex.web;

import com.example.angeldex.model.dtos.ArticleBindingDto;
import com.example.angeldex.model.dtos.EmailRegisterDto;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import com.example.angeldex.model.entities.Article;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.model.enums.RoleNameEnum;
import com.example.angeldex.security.CurrentUser;
import com.example.angeldex.service.ArticleService;
import com.example.angeldex.service.RoleService;
import com.example.angeldex.service.UserEntityService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ArticleController {
    private final CurrentUser currentUser;
    private final ArticleService articleService;
    private final UserEntityService userEntityService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;



    public ArticleController(CurrentUser currentUser, ArticleService articleService, UserEntityService userEntityService, ModelMapper modelMapper, RoleService roleService) {
        this.currentUser = currentUser;
        this.articleService = articleService;
        this.userEntityService = userEntityService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;

    }

    @GetMapping("/articles")
    public String articles(Model model) {
        if (this.currentUser.isAnonymous()) {
            return "redirect:/login";
        }
        if (!model.containsAttribute("emailRegisterDto")) {
            model.addAttribute("emailRegisterDto", new EmailRegisterDto());
        }
        UserEntity user = this.userEntityService.findByEmail(this.currentUser.getUsername());
        if (user.getRoles().stream().anyMatch(role -> role.equals(this.roleService.findRoleByRoleName(RoleNameEnum.ADMIN)))) {
            model.addAttribute("isAdmin", true);
        }else {
            model.addAttribute("isAdmin", false);
        }
        if (!model.containsAttribute("articleBindingDTO")) {
            model.addAttribute("articleBindingDTO", new ArticleBindingDto(this.modelMapper.map(this.currentUser, UserEntity.class)));
        }
        if (!model.containsAttribute("searchArticlesDto")) {
            model.addAttribute("searchArticlesDto", new SearchArticlesDto());
        }
        if (!model.containsAttribute("articleList")){
            model.addAttribute("articleList", this.articleService.findAllArticles());
        }
        return "articles";
    }

    @PostMapping("/articles")
    public String addArticle(@Valid @ModelAttribute ArticleBindingDto articleBindingDTO,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("articleBindingDTO", articleBindingDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.articleBindingDTO", bindingResult);
            return "redirect:/articles";
        }
        Article article = this.modelMapper.map(articleBindingDTO, Article.class);
        UserEntity currentUser = this.userEntityService.findByEmail(this.currentUser.getUsername());
        if (this.articleService.findByTitle(article.getTitle()).isPresent()) {
            return "redirect:/articles";
        }
        this.articleService.createArticle(article.getTitle(), article.getContent(), currentUser, article.getThumbnail(), false);
        currentUser.setArticles(List.of(article));
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String articlesByUser(@PathVariable String id, Model model) {
        if (this.currentUser.isAnonymous()) {
            return "redirect:/login";
        }
        Article article = this.articleService.findArticleById(Long.parseLong(id)).get();
        if (!model.containsAttribute("article")) {
            model.addAttribute("article", article);
        }
        if (!model.containsAttribute("searchArticlesDto")) {
            model.addAttribute("searchArticlesDto", new SearchArticlesDto());
        }
        if (!model.containsAttribute("emailRegisterDto")) {
            model.addAttribute("emailRegisterDto", new EmailRegisterDto());
        }
        return "current-article";
    }
    @GetMapping("/articles/confirm/{id}")
    public String confirmArticle(@PathVariable String id){
        Article article = this.articleService.findArticleById(Long.parseLong(id)).get();
        if (this.articleService.findArticleById(Long.parseLong(id)).isPresent()) {
            article.setConfirmed(true);
            this.articleService.save(article);
        }
        return "redirect:/articles";
    }
    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable String id){
        Article article = this.articleService.findArticleById(Long.parseLong(id)).get();
        if (this.articleService.findArticleById(Long.parseLong(id)).isPresent()) {
            this.articleService.deleteArticleByID(Long.parseLong(id));
        }
        return "redirect:/articles";
    }
    @GetMapping("/search")
    public String searchArticlesByWord(@Valid @ModelAttribute SearchArticlesDto searchArticlesDto,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchArticlesDto", searchArticlesDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.searchArticlesDto", bindingResult);
            return "redirect:/home";
        }
        List<Article> articles = this.articleService.findAllArticlesThatIncludeWord(searchArticlesDto.getWord());
        redirectAttributes.addFlashAttribute("articleList", articles);
        return "redirect:/articles";
    }
}
