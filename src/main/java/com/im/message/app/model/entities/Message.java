package com.im.message.app.model.entities;

import java.util.Date;

public class Message {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private String contentType;
    private String content;
    private Date dateCreated;
    private Long metadataId;

    public Message(Long senderId, Long receiverId, String messageType, String message, Date creationDate, Long metadataId ){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.contentType = messageType;
        this.content = message;
        this.dateCreated = creationDate;
        this.metadataId = metadataId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date creationDate) {
        this.dateCreated = creationDate;
    }

    public Long getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Long metadataId) {
        this.metadataId = metadataId;
    }

}
