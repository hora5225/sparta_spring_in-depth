package com.sparta.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // /login 시 login-form.html 로 리다이렉트
    @GetMapping("/login")
    public String loginT0() {
        return "redirect:/login-form.html";
    }

    @PostMapping("/login")
    public String loginProcess(
            @RequestParam String id,
            @RequestParam String password,
            Model model //Model 을 만들어줘야 model 정보를 저장할 수 있음
    ) {
        if (id.equals(password)) {
            model.addAttribute("loginId", id);
        }
        return "login-result";
    }

}
