package com.learnweb.restfulwebservices.repository;

import com.learnweb.restfulwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUserId(Integer id);
}
