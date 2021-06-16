package com.im.message.app.controller;

import com.im.message.app.action.UserActions;
import com.im.message.app.model.entities.User;
import com.im.message.app.resources.UserResource;
import com.im.message.app.utils.JSONUtil;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Optional;

public class UsersController {
    public static final String USER_NAME_PROPERTY = "username";
    public static final String PASSWORD_PROPERTY = "password";

    private UserActions userActions;

    public UsersController(UserActions userActions){
        this.userActions = userActions;
    }

    public Route createUser = (Request req, Response resp) -> {
        JSONObject body = new JSONObject(req.body());
        Optional<User> user = userActions.createUser(
                body.getString(USER_NAME_PROPERTY),
                body.getString(PASSWORD_PROPERTY));
        if(user.isPresent()){
            resp.status(201);
            return JSONUtil.dataToJson(new UserResource(user.get()));
        }else{
            resp.status(409);
            return "";
        }

    };
}
