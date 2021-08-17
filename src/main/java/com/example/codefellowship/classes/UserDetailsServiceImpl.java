package com.example.codefellowship.classes;

import com.example.codefellowship.classes.ApplicationUser;
import com.example.codefellowship.repositories.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepo applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser appuser = applicationUserRepository.findByUsername(username);
        if (appuser == null){
            throw new UsernameNotFoundException("The user is : " + username + " does not exist");
        }
        return appuser;
    }
}