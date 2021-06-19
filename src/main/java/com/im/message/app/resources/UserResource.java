package com.im.message.app.resources;

import com.im.message.app.model.entities.User;

public class UserResource {

    private Long id;
    private String userName;

    public UserResource(User user) {
        id = user.getId();
        userName = user.getUserName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
