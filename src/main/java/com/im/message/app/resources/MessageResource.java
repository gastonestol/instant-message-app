package com.im.message.app.resources;

import com.im.message.app.model.entities.Message;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.im.message.app.model.entities.MessageDto;

import java.util.Date;

public class MessageResource {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String contentType;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ssZ")
    private Date dateCreated;
    private String senderUserName;

    public MessageResource(MessageDto message) {
        this.id = message.getId();
        this.senderId = message.getSenderId();
        this.receiverId = message.getReceiverId();
        this.contentType = message.getContentType();
        this.content = message.getContent();
        this.dateCreated = message.getDateCreated();
        this.senderUserName = message.getSenderUserName();
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

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

}
