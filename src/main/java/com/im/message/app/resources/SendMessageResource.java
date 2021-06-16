package com.im.message.app.resources;

import com.im.message.app.model.entities.Message;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SendMessageResource {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ssZ")
    private Date dateCreated;

    public SendMessageResource(Message message) {
        this.id = message.getId();
        this.dateCreated = message.getDateCreated();
    }

    public Long getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
