package com.pjh.restfulwebservice.user.service;

import com.pjh.restfulwebservice.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface DaoService {
    List<User> findAll();
    Optional<User> findOne(int id);
    User save(User user);
}
