package com.im.message.app.model.entities;

import java.util.Date;

public class MessageDto extends  Message{

    private String senderUserName;

    public MessageDto(Long senderId, Long receiverId, String messageType, String message, Date creationDate, Long metadataId, String senderUserName) {
        super(senderId, receiverId, messageType, message, creationDate, metadataId);
        this.senderUserName = senderUserName;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }


}
