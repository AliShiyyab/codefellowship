package com.example.codefellowship.controller;

import com.example.codefellowship.repositories.ApplicationUserRepo;
import com.example.codefellowship.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class postController {
    @Autowired
    PostRepo postRepo;
    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @PostMapping("/addpost")
    public RedirectView addNewPost(@RequestParam String body){

    }
}
