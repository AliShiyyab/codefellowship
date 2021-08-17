package com.example.codefellowship.controller;

import com.example.codefellowship.classes.ApplicationUser;
import com.example.codefellowship.classes.Post;
import com.example.codefellowship.repositories.ApplicationUserRepo;
import com.example.codefellowship.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class postController {
    @Autowired
    private ApplicationUserRepo applicationUserRepo;
    @Autowired
    private PostRepo postRepo;

    @PostMapping("/addpost")
    public RedirectView addpost(Principal p , String body){
        ApplicationUser user = applicationUserRepo.findByUsername(p.getName());
        Post newPost = new Post(body, user);
        postRepo.save(newPost);
        return new RedirectView("/myProfile");
    }
//    @PostMapping("/post")
//    public RedirectView newPost(String body , Principal principal){
//        ApplicationUser a = applicationUserRepo.findByUsername(principal.getName());
//        Post newPost = new Post(body,a);
//        postRepo.save(newPost);
//        return new RedirectView("/users/" + a.getId());
//    }
}
