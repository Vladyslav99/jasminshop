package com.andriychuk.demo.controller;

import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.entity.Order;
import com.andriychuk.demo.entity.Product;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/customer/cart")
    public String getCart(Model model) {
        CustomUser user = userService.findByUserName(getCurrentSessionUserName());
        Order order = orderService.findByUserAndStatusCreated(user);
        model.addAttribute("cart", order);
        if (order != null) {
            model.addAttribute("totalPrice", Math.ceil(order.getProductList()
                    .stream()
                    .mapToInt(i -> i.getPrice().intValue())
                    .sum() * (100 - user.getDiscount()) / 100.0));
            model.addAttribute("discount", user.getDiscount());
        }
        return "cart";
    }

    @PostMapping(value = "/customer/add")
    public String addToCart(@RequestParam Long id) {
        List<Product> list = new ArrayList<>();
        list.add(productService.findById(id));
        orderService.save(new Order(null, list, userService.findByUserName(getCurrentSessionUserName()),
                "", OrderStatus.CREATED));

        return "redirect:/";
    }

    @PostMapping(value = "/customer/confirm")
    public String confirm() {
        orderService.saveCompletely(userService.findByUserName(getCurrentSessionUserName()));
        return "redirect:/customer/personal-account";
    }

    @GetMapping(value = "/customer/personal-account")
    public String getPersonal(Model model) {
        model.addAttribute("orders", orderService
                .findAllByUser(userService
                        .findByUserName(getCurrentSessionUserName()))
                .stream()
                .filter(i -> i.getOrderStatus() != OrderStatus.CREATED)
                .collect(Collectors.toList()));
        model.addAttribute("totalPrice", orderService
                .findAllByUser(userService
                        .findByUserName(getCurrentSessionUserName()))
                .stream()
                .mapToInt(i -> i.getProductList().stream().mapToInt(j -> (int) (j.getPrice().intValue() * (100 - userService.findByUserName(getCurrentSessionUserName()).getDiscount()) / 100)).sum())
                .toArray());
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
