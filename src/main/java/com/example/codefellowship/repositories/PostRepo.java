package com.example.codefellowship.repositories;

import com.example.codefellowship.classes.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post,Long> {
}
