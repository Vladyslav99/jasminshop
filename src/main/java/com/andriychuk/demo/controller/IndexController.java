package com.andriychuk.demo.controller;

import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.service.CustomUserDetailsService;
import com.andriychuk.demo.service.OrderService;
import com.andriychuk.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ProductService productService;
    private final OrderService orderService;
    private final CustomUserDetailsService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping(value = "/")
    public String getIndex(Model model) {
        model.addAttribute("products", productService.findAll());
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

    @GetMapping(value = "/personal-account")
    public String getPersonal(Model model) {
        model.addAttribute("orders", orderService.findAllByUser(userService.findByUserName(getCurrentSessionUserName())));
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

    private String getCurrentSessionUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
    }
}
