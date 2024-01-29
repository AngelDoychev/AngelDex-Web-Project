package com.example.angeldex.web;

import com.example.angeldex.config.CMCAPIConfiguration;
import com.example.angeldex.model.dtos.EmailRegisterDto;
import com.example.angeldex.model.dtos.SearchArticlesDto;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.model.enums.RoleNameEnum;
import com.example.angeldex.security.CurrentUser;
import com.example.angeldex.service.CurrencyService;
import com.example.angeldex.service.RoleService;
import com.example.angeldex.service.UserEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CryptoController {
    private final CurrentUser currentUser;
    private final CurrencyService currencyService;
    private final CMCAPIConfiguration cmcapiConfiguration;
    private final UserEntityService userEntityService;
    private final RoleService roleService;



    public CryptoController(CurrentUser currentUser, CurrencyService currencyService, CMCAPIConfiguration cmcapiConfiguration, UserEntityService userEntityService, RoleService roleService) {
        this.currentUser = currentUser;
        this.currencyService = currencyService;
        this.cmcapiConfiguration = cmcapiConfiguration;
        this.userEntityService = userEntityService;
        this.roleService = roleService;

    }

    @GetMapping("/crypto")
    public String crypto(Model model) {
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
        if (!model.containsAttribute("searchArticlesDto")) {
            model.addAttribute("searchArticlesDto", new SearchArticlesDto());
        }
        model.addAttribute("cryptoList", this.currencyService.findAllCurrencies());
        return "crypto";
    }
    @GetMapping("/crypto/update")
    public String resetTopCryptos() {
        this.cmcapiConfiguration.hardUpdateCurrencies();
        return "redirect:/crypto";
    }
    @GetMapping("/crypto/update/hard")
    public String hardResetTopCryptos() {
        this.cmcapiConfiguration.updateCurrencies();
        return "redirect:/crypto";
    }

}