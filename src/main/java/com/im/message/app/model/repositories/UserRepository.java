package com.im.message.app.model.repositories;

import com.im.message.app.model.entities.User;

import java.util.Optional;

public interface UserRepository {
    User createUser(String userName, String password);
    Optional<User> getUser(String userName);
}
