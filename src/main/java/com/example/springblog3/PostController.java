package com.example.springblog3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController
{
    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex()
    {
        return "This is the posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String posts(@PathVariable long id)
    {
        return "Viewing post: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createGet()
    {
        return "Viewing form for creating post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost()
    {
        return "Create a new post";
    }
}
