package com.im.message.app.model.repositories;

import com.im.message.app.model.entities.Message;
import com.im.message.app.model.entities.MessageDto;

import java.util.List;

public interface MessageRepository {
    Message saveMessage(Long senderId, Long receiverId, String type, String content, Long metadataId);
    List<MessageDto> getMessages(Long receiverId, Long startId, Long limit);
    Long setMessageAsAnsewerd(Long messageId);
}
