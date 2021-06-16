package com.im.message.app.resources;

import com.im.message.app.model.entities.Message;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MessageResource {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String contentType;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ssZ")
    private Date dateCreated;

    public MessageResource(Message message) {
        this.id = message.getId();
        this.senderId = message.getSenderId();
        this.receiverId = message.getReceiverId();
        this.contentType = message.getContentType();
        this.content = message.getContent();
        this.dateCreated = message.getDateCreated();
    }

    public Long getId() {
        return id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContent() {
        return content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
