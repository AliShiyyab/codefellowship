package com.example.codefellowship.controller;

import com.example.codefellowship.classes.ApplicationUser;
import com.example.codefellowship.repositories.ApplicationUserRepo;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;


@Controller
public class applicationUserController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @GetMapping("/")
    public String getHomePage(Model m , Principal principal){
        return "home.html";
    }

    @GetMapping("/users/{id}")
    public String getUserPage(Model m , @PathVariable Long id){
        m.addAttribute("user" , applicationUserRepo.findById(id).get());
        return "profile.html";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("appUsers" , applicationUserRepo.findAll());
        return "users" ;
    }


    @GetMapping("/myProfile")
    public  String getMyProfilePage(Principal p,Model m){
        ApplicationUser user = applicationUserRepo.findByUsername(p.getName());
        m.addAttribute("user",user);
        return "profile.html";
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signUp")
    public String getSignUp(){
        return "signUp.html";
    }

    @GetMapping("/signIn")
    public String getSignIn(){
        return "signIn.html";
    }

    @PostMapping("/signUp")
    public RedirectView postSignUp(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String dateOfBirth,
                                   @RequestParam String bio){
        ApplicationUser appUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        applicationUserRepo.save(appUser);
        return new RedirectView("/signIn");
    }

    @PostMapping("/follow/{id}")
    public RedirectView followUser(Principal p,@PathVariable("id") Long id){
        ApplicationUser following = applicationUserRepo.findById(id).get();
        ApplicationUser follower= applicationUserRepo.findByUsername(p.getName());
        follower.addFollowing(following);
        applicationUserRepo.save(follower);
        return  new RedirectView("/users/"+id);
    }

    @GetMapping("/feed")
    public String showFollower(Principal principal, Model model){
        ApplicationUser applicationUser = applicationUserRepo.findByUsername(principal.getName());
        model.addAttribute("followingUser" , applicationUser.getFollowers());
        return "feed.html";

    }

}
