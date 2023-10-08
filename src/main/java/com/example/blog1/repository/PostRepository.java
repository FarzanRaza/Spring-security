package com.example.blog1.repository;

import com.example.blog1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>{

}
