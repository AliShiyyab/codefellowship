package com.example.codefellowship.controller;

import com.example.codefellowship.classes.ApplicationUser;
import com.example.codefellowship.repositories.ApplicationUserRepo;
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


@Controller
public class applicationUserController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @GetMapping("/")
    public String getHomePage(Model m){
//        m.addAttribute("userInfo" , applicationUserRepo.findByUsername("ALI"));
        return "home.html";
    }

    @GetMapping("/users/{id}")
    public String getUserPage(Model m , @PathVariable Long id){
        m.addAttribute("appId" , applicationUserRepo.findById(id).orElseThrow());
        return "profile.html";
    }


    @GetMapping("/myProfile")
    public  String getMyProfilePage(Model m){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser appUser = applicationUserRepo.findByUsername(userDetails.getUsername());
        m.addAttribute("appUser" , appUser);
        m.addAttribute("post" , appUser.getPost());
        m.addAttribute("showLogout" , true);
        m.addAttribute("username" , appUser.getUsername());
        m.addAttribute("showUsername" , true);
        return "profile";
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
        return new RedirectView("signIn");
    }
}
