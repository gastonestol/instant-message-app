package com.im.message.app.model.implementations;

import com.im.message.app.model.entities.Message;
import com.im.message.app.model.entities.MessageDto;
import com.im.message.app.model.repositories.MessageRepository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {

    private Sql2o sql2o;

    public MessageRepositoryImpl() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db",null,null);
        createTable();
    }

    @Override
    public Message saveMessage(Long senderId, Long receiverId, String contentType, String content, Long metadataId) {
        try (Connection conn = sql2o.open()) {
            Message message = new Message(senderId,receiverId,contentType,content,new Date(),metadataId);
            Long id = conn.createQuery("insert into messages" +
                    "( sender_id,receiver_id,contentType,content, date_created, metadata_id, answered) " +
                    "VALUES (:sender_id,:receiver_id,:contentType,:content, :date_created, :metadata_id, 0)")
                    .addParameter("sender_id", message.getSenderId())
                    .addParameter("receiver_id", message.getReceiverId())
                    .addParameter("contentType", message.getContentType())
                    .addParameter("content", message.getContent())
                    .addParameter("date_created", message.getDateCreated())
                    .addParameter("date_created", message.getDateCreated())
                    .addParameter("metadata_id", message.getMetadataId())
                    .executeUpdate()
                    .getKey(Long.class);
            message.setId(id);
            return message;
        }
    }

    @Override
    public List<MessageDto> getMessages(Long receiverId, Long startId, Long limit) {
        try (Connection conn = sql2o.open()) {
            List<MessageDto> messages = conn
                .createQuery("select m.id,m.sender_id,m.receiver_id,m.contentType,m.content,m.date_created,m.metadata_id,u.user_name " +
                        "from messages m left join users u on m.sender_id = u.id " +
                        "where receiver_id = :receiver_id and m.id > :start_id and answered = 0 " +
                        "Limit :limit")
                .addParameter("receiver_id", receiverId)
                .addParameter("start_id", startId)
                .addParameter("limit", limit)
                .addColumnMapping("sender_id","senderId")
                .addColumnMapping("receiver_id","receiverId")
                .addColumnMapping("date_created","dateCreated")
                .addColumnMapping("metadata_id","metadataId")
                .addColumnMapping("user_name","senderUserName")
                .executeAndFetch(MessageDto.class);
            return messages;
        }catch (Exception e){
            System.out.println(e.getCause());
            return new ArrayList<>();
        }
    }

    @Override
    public Long setMessageAsAnsewerd(Long messageId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("update messages" +
                    " set answered = 1 where id = :id" +
                    "VALUES (:sender_id,:receiver_id,:contentType,:content, :date_created, :metadata_id, 0)")
                    .addParameter("id",messageId)
                    .executeUpdate()
                    .getKey(Long.class);
        }
    }

    private void createTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("CREATE TABLE IF NOT EXISTS messages (" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "   sender_id INTEGER NOT NULL," +
                    "   receiver_id INTEGER NOT NULL," +
                    "   contentType text NOT NULL," +
                    "   content text NOT NULL, " +
                    "   answered NUMERIC NOT NULL, " +
                    "   date_created real NOT NULL," +
                    "   metadata_id INTEGER " +
                    ")"
            ).executeUpdate();
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
}
