package com.example.codefellowship.repositories;

import com.example.codefellowship.classes.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepo extends CrudRepository<ApplicationUser , Long> {
    public ApplicationUser findByUsername(String username);
}
