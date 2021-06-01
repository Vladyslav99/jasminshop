package com.andriychuk.demo.controller;

import com.andriychuk.demo.dto.CustomUserDTO;
import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping()
    public String getAdminPage(@ModelAttribute CustomUserDTO customUserDTO) {
        return "admin";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@ModelAttribute CustomUserDTO customUserDTO) {



        customUserDetailsService.saveCustomer(customUserDTO);
        return "redirect:/admin";
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
