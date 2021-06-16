package com.im.message.app;

import com.im.message.app.action.MessageActions;
import com.im.message.app.model.entities.Message;
import com.im.message.app.model.entities.Metadata;
import com.im.message.app.services.MessageService;
import com.im.message.app.services.MetadataService;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class MessagesTests {

    private Message messageWithoutMetadata = new Message(
            1L,2L,"text","Hello world!",new Date(),null);
    private Message messageWithMetadata = new Message(
            1L,2L,"Image","http://test.org/test.jpg",new Date(),1L);
    private Metadata metadata = new Metadata(messageWithMetadata.getContent()
            ,"image/jpg",1024L,"mock.jpg", new Date() );

    @Test
    public void givenAMessageWithTextContentWhenSaveMessageThenDoNotSaveMetadataAndReturnMessage(){
        MetadataService metadataService = EasyMock.createMock(MetadataService.class);
        MessageService messageService = EasyMock.createMock(MessageService.class);
        expect(metadataService.getMetadataFromResouce(messageWithoutMetadata.getContentType(),
                messageWithoutMetadata.getContent())).andReturn(Optional.empty());
        messageWithoutMetadata.setId(1L);
        expect(messageService.saveMessage(messageWithoutMetadata.getSenderId(),messageWithoutMetadata.getReceiverId(),
                messageWithoutMetadata.getContentType(),messageWithoutMetadata.getContent(),null))
                .andReturn(messageWithoutMetadata);

        replay(metadataService);
        replay(messageService);

        MessageActions actions = new MessageActions(messageService,metadataService);
        Message result = actions.saveMessage(
                messageWithoutMetadata.getSenderId(),
                messageWithoutMetadata.getReceiverId(),
                messageWithoutMetadata.getContentType(),
                messageWithoutMetadata.getContent());
        assertEquals(result.getId(),messageWithoutMetadata.getId());

        verify(metadataService);
        verify(messageService);

    }

    @Test
    public void givenAMessageWithImageContentWhenSaveMessageThenSaveMetadataAndReturnMessage(){
        MetadataService metadataService = EasyMock.createMock(MetadataService.class);
        MessageService messageService = EasyMock.createMock(MessageService.class);
        expect(metadataService.getMetadataFromResouce(messageWithoutMetadata.getContentType(),
                messageWithoutMetadata.getContent())).andReturn(Optional.of(metadata));
        metadata.setId(1L);
        expect(metadataService.saveMetadata(metadata)).andReturn(metadata);
        messageWithoutMetadata.setId(1L);
        expect(messageService.saveMessage(messageWithoutMetadata.getSenderId(),messageWithoutMetadata.getReceiverId(),
                messageWithoutMetadata.getContentType(),messageWithoutMetadata.getContent(),metadata.getId()))
                .andReturn(messageWithoutMetadata);

        replay(metadataService);
        replay(messageService);

        MessageActions actions = new MessageActions(messageService,metadataService);
        Message result = actions.saveMessage(
                messageWithoutMetadata.getSenderId(),
                messageWithoutMetadata.getReceiverId(),
                messageWithoutMetadata.getContentType(),
                messageWithoutMetadata.getContent());
        assertEquals(result.getId(),messageWithoutMetadata.getId());

        verify(metadataService);
        verify(messageService);

    }

}
