package com.andriychuk.demo.controller;

import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.service.CustomUserDetailsService;
import com.andriychuk.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ProductService productService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping(value = "/")
    public String getIndex(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
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

    @GetMapping(value = "/personal-account")
    public String getPersonal() {
        return "personal";
    }

}
