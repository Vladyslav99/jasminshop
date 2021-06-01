package com.andriychuk.demo.controller;

import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping(value = "/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(value = "/login-failure")
    public String getLoginFailureMessage(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @GetMapping(value = "/personal")
    public String getPersonal() {
        return "personal";
    }


    @GetMapping("/create-admin")
    public String createAdmin() {
        CustomUser admin = CustomUser.builder()
                .login("admin")
                .password("11111")
                .role(CustomUser.Role.ADMIN)
                .build();
        customUserDetailsService.save(admin);
        return "login";
    }
}
