package com.example.springblog3;

import com.example.springblog3.models.Post;
import com.example.springblog3.models.User;
import com.example.springblog3.repositories.PostRepository;
import com.example.springblog3.repositories.UserRepository;
import com.example.springblog3.services.AdService;
import com.example.springblog3.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private PostRepository postDao;

    private UserRepository userDao;

    private EmailService emailService;



    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {

        Post singlePost = postDao.getById(id);
        model.addAttribute("post", singlePost);
        return "posts/show";
    }


    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post){

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(currentUser);
        postDao.save(post);

        emailService.prepareAndSend(post, "New Post Created!", "A new post has been created! Here is the title of your new post! Title: " + post.getTitle());

        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model){
        model.addAttribute("postToEdit", postDao.getReferenceById(id));
        return "posts/editPost";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postDao.delete(postDao.getReferenceById(id));
        return "redirect:/posts";
    }
}
