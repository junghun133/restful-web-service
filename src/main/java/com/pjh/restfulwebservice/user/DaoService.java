package com.pjh.restfulwebservice.user;

import java.util.List;

public interface DaoService {
    List<User> findAll();
    User findOne(int id);
    User save(User user);
}
