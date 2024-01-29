package com.example.angeldex.web;

import com.example.angeldex.model.dtos.EmailRegisterDto;
import com.example.angeldex.model.dtos.RegisterUserBindingDto;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import com.example.angeldex.model.entities.RegisteredEmail;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.model.enums.RoleNameEnum;
import com.example.angeldex.security.CurrentUser;
import com.example.angeldex.service.RegisteredEmailService;
import com.example.angeldex.service.RoleService;
import com.example.angeldex.service.UserEntityService;
import com.example.angeldex.util.JavaMailSender;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RegisterController {
    private final UserEntityService userEntityService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final RoleService roleService;
    private final RegisteredEmailService registeredEmailService;

    public RegisterController(UserEntityService userEntityService, ModelMapper modelMapper, CurrentUser currentUser, RoleService roleService, RegisteredEmailService registeredEmailService) {
        this.userEntityService = userEntityService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.roleService = roleService;
        this.registeredEmailService = registeredEmailService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        if (!this.currentUser.isAnonymous()) {
            return "redirect:/home";
        } else {
            if (!model.containsAttribute("registerServiceModel")) {
                model.addAttribute("registerServiceModel", new RegisterUserBindingDto());
                model.addAttribute("emailRegisterDto", new EmailRegisterDto());
            }
            if (!model.containsAttribute("searchArticlesDto")) {
                model.addAttribute("searchArticlesDto", new SearchArticlesDto());
            }
            return "register";
        }
    }

    @PostMapping("/register")
    private String onRegister(@Valid @ModelAttribute RegisterUserBindingDto registerServiceModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (null != this.userEntityService.findByEmail(registerServiceModel.getEmail())){
            bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
            redirectAttributes.addFlashAttribute("registerServiceModel", registerServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerServiceModel", bindingResult);
            return "redirect:/register";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerServiceModel", registerServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerServiceModel", bindingResult);
            return "redirect:/register";
        }
        UserEntity user = this.modelMapper.map(registerServiceModel, UserEntity.class);
        this.userEntityService.createUserEntity(user.getEmail(), user.getPasswordHash(), List.of(this.roleService.findRoleByRoleName(RoleNameEnum.USER)));
        JavaMailSender.sendEmailUponEmailRegistration(user);
        return "redirect:/login";
    }

    @GetMapping("/registerEmail")
    private String registerEmail(@Valid @ModelAttribute EmailRegisterDto emailRegisterDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("emailRegisterDto", emailRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.emailRegisterDto", bindingResult);
            return "redirect:/home";
        }
        if (null != this.registeredEmailService.findByEmail(emailRegisterDto.getEmail())){
            bindingResult.rejectValue("email", "error.user", "This email has already been registered.");
            redirectAttributes.addFlashAttribute("emailRegisterDto", emailRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.emailRegisterDto", bindingResult);
            return "redirect:/home";
        }
        RegisteredEmail registeredEmail = this.modelMapper.map(emailRegisterDto, RegisteredEmail.class);
        this.registeredEmailService.save(registeredEmail);
        JavaMailSender.sendEmailUponEmailRegistration(registeredEmail.getEmail());
        return "redirect:/";
    }
}
