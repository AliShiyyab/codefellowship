package com.example.codefellowship.controller;

import com.example.codefellowship.classes.ApplicationUser;
import com.example.codefellowship.repositories.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class applicationUserController {
    @Autowired
    ApplicationUserRepo applicationUserRepo;

}
