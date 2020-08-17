package com.pjh.restfulwebservice.user;

import java.util.List;
import java.util.Optional;

public interface DaoService {
    List<User> findAll();
    Optional<User> findOne(int id);
    User save(User user);
}
