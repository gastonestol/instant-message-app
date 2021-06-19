package com.im.message.app.action;

import com.im.message.app.model.entities.User;
import com.im.message.app.services.TokenService;
import com.im.message.app.services.UserService;

import java.util.Optional;

public class UserActions {

    private UserService userService;
    private TokenService tokenService;

    public UserActions(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public Optional<User> createUser(String userName, String password){
        String encryptedPassword = tokenService.hashPassword(password);
        return userService.createUser(userName,encryptedPassword);
    }

    public Optional<User> getUser(String userName){
        return userService.getUser(userName);
    }

}
