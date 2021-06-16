package com.im.message.app.model.repositories;

import com.im.message.app.model.entities.Message;

import java.util.List;

public interface MessageRepository {
    Message saveMessage(Long senderId, Long receiverId, String type, String content, Long metadataId);
    List<Message> getMessages(Long receiverId,Long startId, Long limit);
}
