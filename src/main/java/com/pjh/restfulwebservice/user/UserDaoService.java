package com.pjh.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class UserDaoService implements DaoService{
    //database
    private static final List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static{
        users.add(new User(1, "JungHoon, Park", new Date()));
        users.add(new User(2, "JungSoon, Park", new Date()));
        users.add(new User(3, "JungBoon, Park", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public Optional<User> findOne(int id){
         return users.stream()
                .filter( u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public User save(User user) {
        if(user.getId() == null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
