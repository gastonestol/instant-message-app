package com.im.message.app.action;

import com.im.message.app.exceptions.UserNotFoundException;
import com.im.message.app.model.entities.User;
import com.im.message.app.resources.LoginResource;
import com.im.message.app.services.TokenService;
import com.im.message.app.services.UserService;

import java.util.Optional;

public class AuthActions {

    private TokenService tokenService;
    private UserService userService;

    public AuthActions(UserService userService,TokenService tokenService){
        this.tokenService = tokenService;
        this.userService = userService;
    }

    public LoginResource login(String userName,String password) throws UserNotFoundException{
        Optional<User> user = userService.getUser(userName);
        if (user.isPresent() && tokenService.checkPassword(password,user.get().getPassword())) {
            String token = tokenService.newToken(user.get());
            return new LoginResource(user.get(),token);
        }else{
            throw new UserNotFoundException();
        }
    }
}
