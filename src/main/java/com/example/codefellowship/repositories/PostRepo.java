package com.example.codefellowship.repositories;

import com.example.codefellowship.classes.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Post,Long> {
}
