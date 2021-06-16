package com.im.message.app.services;

import com.im.message.app.model.entities.User;
import com.im.message.app.model.repositories.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String userName){
        return userRepository.getUser(userName);
    }

    public Optional<User>  createUser(String userName, String password){
        if(!getUser(userName).isPresent()){
            System.out.println("creating user "+userName+" - "+password);
            return Optional.of(userRepository.createUser(userName,password));
        }else{
            return Optional.empty();
        }
    }

}
