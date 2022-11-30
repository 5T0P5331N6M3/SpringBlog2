package com.example.springblog3;

import com.example.springblog3.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final UserRepository userDao;

    public AuthController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

}