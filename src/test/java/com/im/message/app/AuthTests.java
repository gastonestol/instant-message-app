package com.im.message.app;

import com.im.message.app.action.AuthActions;
import com.im.message.app.exceptions.UserNotFoundException;
import com.im.message.app.model.entities.User;
import com.im.message.app.resources.LoginResource;
import com.im.message.app.services.TokenService;
import com.im.message.app.services.UserService;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class AuthTests {

    private User correctUser = new User("gaston","hash pass",new Date());


    @Test
    public void givenAnExistingUserAndCorrectPasswordWhenLoginThenReturnUserIdAndToken(){
        TokenService tokenService = EasyMock.createMock(TokenService.class);
        UserService userService = EasyMock.createMock(UserService.class);
        correctUser.setId(1L);
        String testPass = "test pass";
        String token = "Bearer token";
        LoginResource resource = new LoginResource(correctUser,token);

        expect(userService.getUser(correctUser.getUserName())).andReturn(Optional.of(correctUser));
        expect(tokenService.checkPassword(testPass,correctUser.getPassword())).andReturn(true);
        expect(tokenService.newToken(correctUser)).andReturn(token);

        replay(userService);
        replay(tokenService);

        AuthActions actions = new AuthActions(userService, tokenService);
        LoginResource result = actions.login(correctUser.getUserName(),testPass);
        assertEquals(resource.getToken(),result.getToken());
        assertEquals(resource.getId(),result.getId());

        verify(userService);
        verify(tokenService);

    }

    @Test
    public void givenAnNonExistingUserWhenLoginThenThrowException(){
        TokenService tokenService = EasyMock.createMock(TokenService.class);
        UserService userService = EasyMock.createMock(UserService.class);
        String nonExistentUserName = "user";
        expect(userService.getUser(nonExistentUserName)).andReturn(Optional.empty());

        replay(userService);
        replay(tokenService);

        AuthActions actions = new AuthActions(userService, tokenService);
        assertThrows(UserNotFoundException.class,() -> actions.login(nonExistentUserName,"test"));

        verify(userService);
        verify(tokenService);

    }

}
