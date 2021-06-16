package com.im.message.app.model.entities;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private String userName;
    private String password;
    private Date dateCreated;

    public User(String userName, String password, Date dateCreated) {
        this.userName = userName;
        this.password = password;
        this.dateCreated = dateCreated;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
