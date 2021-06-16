package com.im.message.app.services;

import com.im.message.app.model.entities.Message;
import com.im.message.app.model.repositories.MessageRepository;
import java.util.List;

public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Long senderId, Long receiverId, String contentType, String content, Long metadataId) {
        return messageRepository.saveMessage(senderId,receiverId,contentType,content,metadataId);
    }

    public List<Message> getMessages(Long receiverId, Long startId, Long limit){
        return messageRepository.getMessages(receiverId,startId,limit);

    }
}
