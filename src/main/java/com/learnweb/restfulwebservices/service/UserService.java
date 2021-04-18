package com.learnweb.restfulwebservices.service;

import com.learnweb.restfulwebservices.entity.User;
import com.learnweb.restfulwebservices.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    private static List<User> users= new ArrayList<>();

    private static  int userCount=3;

  /*  static *//*{
        users.add(new User(1,"Adam", LocalDate.of(1995, 7, 18)));
        users.add(new User(2,"Eve", LocalDate.of(1994, 6, 17)));
        users.add(new User(3,"Jack", LocalDate.of(1993, 5, 16)));
    }*/

    public List<User> findAll(){
        return users;
    }

    public User save(User userToBeSaved){
        if(userToBeSaved.getId()==null){
            userToBeSaved.setId(++userCount);
        }
        users.add(userToBeSaved);
        return userToBeSaved;
    }

    public User findOne(int id) throws UserNotFoundException{
        return users.stream()
                .filter(user -> user.getId()==id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format("No User found with id:: %s", id)));
    }

    public User deleteUser(int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
