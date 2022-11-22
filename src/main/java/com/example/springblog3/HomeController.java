package com.example.springblog3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController
{
    @GetMapping("/")
    @ResponseBody
    public String landing()
    {
        return "This is the landing page.";
    }

    @GetMapping("/home")
    public String welcome()
    {
        return "home";
    }
}
