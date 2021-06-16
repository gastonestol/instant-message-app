package com.im.message.app.resources;

import com.im.message.app.model.entities.User;

public class UserResource {

    private Long id;

    public UserResource(User user) {
        id = user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
