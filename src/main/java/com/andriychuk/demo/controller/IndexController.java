package com.andriychuk.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

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

    @GetMapping(value = "/personal")
    public String getPersonal() {
        return "personal";
    }
}
