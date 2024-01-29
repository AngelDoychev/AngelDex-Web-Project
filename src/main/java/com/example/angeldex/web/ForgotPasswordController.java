package com.example.angeldex.web;

import com.example.angeldex.model.dtos.ChangePasswordDto;
import com.example.angeldex.model.dtos.EmailRegisterDto;
import com.example.angeldex.model.dtos.ForgotPasswordDto;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.service.UserEntityService;
import com.example.angeldex.util.JavaMailSender;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {
    private final UserEntityService userEntityService;
    private final PasswordEncoder passwordEncoder;
    private int confirmationNumber;
    private UserEntity currentUser;

    public ForgotPasswordController(UserEntityService userEntityService, PasswordEncoder passwordEncoder) {
        this.userEntityService = userEntityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        if (!model.containsAttribute("forgotPasswordDto")) {
            model.addAttribute("forgotPasswordDto", new ForgotPasswordDto());
        }
        if (!model.containsAttribute("changePasswordDto")) {
            model.addAttribute("changePasswordDto", new ChangePasswordDto());
        }
        if (!model.containsAttribute("emailRegisterDto")) {
            model.addAttribute("emailRegisterDto", new EmailRegisterDto());
        }
        if (!model.containsAttribute("searchArticlesDto")) {
            model.addAttribute("searchArticlesDto", new SearchArticlesDto());
        }
        return "forgot-password";
    }

    @PostMapping("/forgotPassword")
    public String sentEmailForgetPassword(@Valid @ModelAttribute ForgotPasswordDto forgotPasswordDto,
                                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("forgotPasswordDto", forgotPasswordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.forgotPasswordDto", bindingResult);
            return "redirect:/forgotPassword";
        }
        if (null == this.userEntityService.findByEmail(forgotPasswordDto.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "An account with that email doesn't exist.");
            redirectAttributes.addFlashAttribute("forgetPasswordDto", forgotPasswordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.forgetPasswordDto", bindingResult);
            return "redirect:/forgotPassword";
        }
        UserEntity forgottenUser = this.userEntityService.findByEmail(forgotPasswordDto.getEmail());
        setConfirmationNumber(JavaMailSender.sendEmailChangePasswordRequest(forgottenUser));
        setCurrentUser(forgottenUser);
        return "redirect:/forgotPassword?sent=success";
    }

    @PostMapping("/forgotPassword/changePassword")
    public String changePassword(@ModelAttribute ChangePasswordDto changePasswordDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("changePasswordDto", changePasswordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordDto", changePasswordDto);
            return "redirect:/forgotPassword?sent=success";
        }
        if (0 == changePasswordDto.getCode()) {
            bindingResult.rejectValue("code", "error.user", "Please enter the code from your email.");
            redirectAttributes.addFlashAttribute("changePasswordDto", changePasswordDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordDto", bindingResult);

        }
        if (changePasswordDto.getCode() == getConfirmationNumber()) {
            UserEntity user = this.userEntityService.findByEmail(getCurrentUser().getEmail());
            user.setPasswordHash(this.passwordEncoder.encode(changePasswordDto.getNewPassword()));
            this.userEntityService.save(user);
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("changePasswordDto", changePasswordDto);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordDto", bindingResult);
        return "redirect:/forgotPassword?sent=success";

    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
