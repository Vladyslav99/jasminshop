package com.andriychuk.demo.controller;

import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.entity.Order;
import com.andriychuk.demo.enums.OrderStatus;
import com.andriychuk.demo.service.CustomUserDetailsService;
import com.andriychuk.demo.service.OrderService;
import com.andriychuk.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

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

    @GetMapping(value = "/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(value = "/login-failure")
    public String getLoginFailureMessage(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @GetMapping(value = "/cart")
    public String getCart(Model model) {
        model.addAttribute("cart", orderService.findByUserAndStatusCreated(userService.findByUserName(getCurrentSessionUserName())));
        return "card";
    }

    @PostMapping(value = "/add")
    public String addToCart(@RequestParam Long id) {
        orderService.save(new Order(null, Collections.singletonList(productService.findById(id)),
                userService.findByUserName(getCurrentSessionUserName()),
                "", OrderStatus.CREATED));

        return "redirect:/";
    }

    @PostMapping(value = "/confirm")
    public String confirm() {
        orderService.saveCompletely(userService.findByUserName(getCurrentSessionUserName()));

        return "redirect:/personal-account";
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
        customUserDetailsService.saveAdmin(admin);
        return "login";
    }

    private String getCurrentSessionUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
    }
}
