package com.pjh.restfulwebservice.user.jpa;

import com.pjh.restfulwebservice.user.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
