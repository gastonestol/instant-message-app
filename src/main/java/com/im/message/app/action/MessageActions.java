package com.im.message.app.action;

import com.im.message.app.model.entities.Message;
import com.im.message.app.model.entities.Metadata;
import com.im.message.app.services.MetadataService;
import com.im.message.app.services.MessageService;

import java.util.List;
import java.util.Optional;

public class MessageActions {

    private final MessageService messageService;
    private final MetadataService metadataService;

    public MessageActions(MessageService messageService, MetadataService metadataService) {
        this.messageService = messageService;
        this.metadataService = metadataService;
    }

    public Message saveMessage(Long senderId, Long receiverId, String contentType, String content) {
        Optional<Metadata> optionalMetadata = metadataService.getMetadataFromResouce(contentType,content);
        Long metadataId = null;
        if(optionalMetadata.isPresent()) {
            metadataId = metadataService.saveMetadata(optionalMetadata.get()).getId();
        }
        return messageService.saveMessage(senderId,receiverId,contentType,content,metadataId);
    }
    public List<Message> getMessages(Long receiverId, Long startId, Long limit) {
        return messageService.getMessages(receiverId,startId,limit);
    }

}
