package com.im.message.app.filter;

import com.im.message.app.services.TokenService;
import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class TokenValidatorFilter implements Filter {
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String USERS_ENDPOINT = "/users";

    private static final String HTTP_POST = "POST";
    private static final String HTTP_OPTIONS = "OPTIONS";

    private final TokenService tokenService;

    public TokenValidatorFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }

    private boolean isLoginRequest(Request request) {
        return request.uri().equals(LOGIN_ENDPOINT) && request.requestMethod().equals(HTTP_POST);
    }

    private boolean isOptionsRequest(Request request) {
        return request.requestMethod().equals(HTTP_OPTIONS);
    }

    private boolean isCreateUser(Request request) {
        return request.uri().equals(USERS_ENDPOINT) && request.requestMethod().equals(HTTP_POST);
    }


    @Override
    public void handle(Request req, Response res) {
        if (!isLoginRequest(req) && !isOptionsRequest(req) && !isCreateUser(req)) {
            String authorizationHeader = req.headers("Authorization");
            if (authorizationHeader == null) {
                halt(401);
            } else if (!tokenService.validateToken(authorizationHeader.replace(TOKEN_PREFIX, ""))) {
                halt(401);
            }
        }
    }
}
