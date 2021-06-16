package com.im.message.app.resources;

import com.im.message.app.model.entities.User;

public class LoginResource {
    private Long id;
    private String token;

    public LoginResource(User user,String token) {
        id = user.getId();
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
