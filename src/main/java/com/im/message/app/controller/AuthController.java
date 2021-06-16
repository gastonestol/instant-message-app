package com.im.message.app.controller;

import com.im.message.app.action.AuthActions;
import com.im.message.app.resources.LoginResource;
import com.im.message.app.utils.JSONUtil;
import org.json.JSONObject;
import spark.*;

import java.util.stream.Collectors;

public class AuthController {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String USER_NAME_PROPERTY = "username";
    public static final String PASSWORD_PROPERTY = "password";

    private AuthActions authActions;

    public AuthController(AuthActions authActions){
        this.authActions = authActions;
    }

    public Route login = (Request req, Response resp) -> {
        String json = req.raw().getReader().lines().collect(Collectors.joining());
        JSONObject jsonRequest = new JSONObject(json);
        String userName = jsonRequest.getString(USER_NAME_PROPERTY);
        String password = jsonRequest.getString(PASSWORD_PROPERTY);
        try {
            LoginResource resource = authActions.login(userName,password);
            resp.header(AUTHORIZATION_HEADER, TOKEN_PREFIX + " " + resource.getToken());
            return JSONUtil.dataToJson(resource);
        } catch (Exception e) {
            resp.status(401);
        }
        return "";
    };


}
