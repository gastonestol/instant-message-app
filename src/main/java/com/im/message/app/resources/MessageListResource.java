package com.im.message.app.resources;

import java.util.List;

public class MessageListResource {
    List<MessageResource> messages;

    public MessageListResource(List<MessageResource> messageList) {
        messages = messageList;
    }

    public List<MessageResource> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResource> messages) {
        this.messages = messages;
    }
}
