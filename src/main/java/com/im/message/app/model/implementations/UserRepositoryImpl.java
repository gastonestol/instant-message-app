package com.im.message.app.model.implementations;

import com.im.message.app.model.entities.User;
import com.im.message.app.model.repositories.UserRepository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private Sql2o sql2o;

    public UserRepositoryImpl() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db",null,null);
        createTable();
    }

    @Override
    public User createUser(String userName, String password) {
        try (Connection conn = sql2o.open()) {
            System.out.println("Open!");
            User user = new User(userName,password,new Date());
            Long id = conn.createQuery("insert into users( user_name, password, date_created) " +
                    "VALUES (:userName, :password, :dateCreated)")
                    .addParameter("userName", user.getUserName())
                    .addParameter("password", user.getPassword())
                    .addParameter("dateCreated", user.getDateCreated())
                    .executeUpdate()
                    .getKey(Long.class);
            user.setId(id);
            return user;
        }
    }

    @Override
    public Optional<User> getUser(String userName) {
        try (Connection conn = sql2o.open()) {
            User user = conn.createQuery("select * from users " +
                    "where user_name = :userName")
                    .addParameter("userName", userName)
                    .addColumnMapping("user_name","userName")
                    .addColumnMapping("date_created","dateCreated")
                    .executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    private void createTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "   user_name text NOT NULL," +
                    "   password text NOT NULL," +
                    "   date_created real NOT NULL" +
                    ")"
            ).executeUpdate();
        }
    }

}
