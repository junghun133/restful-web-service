package com.pjh.restfulwebservice.user.jpa;

import com.pjh.restfulwebservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
