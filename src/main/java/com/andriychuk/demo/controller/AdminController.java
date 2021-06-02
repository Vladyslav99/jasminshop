package com.andriychuk.demo.controller;

import com.andriychuk.demo.dto.CustomUserDTO;
import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.service.CustomUserDetailsService;
import com.andriychuk.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/add-customer")
    public String getAddCustomerPage(@ModelAttribute CustomUserDTO customUserDTO) {
        return "add-customer";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@ModelAttribute CustomUserDTO customUserDTO) {
        customUserDetailsService.saveCustomer(customUserDTO);
        return "redirect:/admin/add-customer";
    }

    @GetMapping("/current-orders")
    public String showOrdersPage(Model model) {
        model.addAttribute("orders", orderService.findAll());
        if (orderService.findAll() != null){
        model.addAttribute("totalPrice", orderService.findAll()
                .stream()
                .mapToInt(i -> i.getProductList()
                        .stream()
                        .mapToInt(j -> (int) (j.getPrice().intValue() * (100 - (i.getUser().getDiscount() != null ? i.getUser().getDiscount() : 0)) / 100))
                        .sum())
                .toArray());
        }
        return "admin-orders";
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
}
