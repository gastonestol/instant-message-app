package com.im.message.app.model.implementations;

import com.im.message.app.model.entities.Message;
import com.im.message.app.model.repositories.MessageRepository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

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
                    "( sender_id,receiver_id,contentType,content, date_created, metadata_id) " +
                    "VALUES (:sender_id,:receiver_id,:contentType,:content, :date_created, :metadata_id)")
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
    public List<Message> getMessages(Long receiverId, Long startId, Long limit) {
        try (Connection conn = sql2o.open()) {
            List<Message> messages = conn
                .createQuery("select * from messages " +
                        "where receiver_id = :receiver_id and id >= :start_id " +
                        "Limit :limit")
                .addParameter("receiver_id", receiverId)
                .addParameter("start_id", startId)
                .addParameter("limit", limit)
                .addColumnMapping("sender_id","senderId")
                .addColumnMapping("receiver_id","receiverId")
                .addColumnMapping("date_created","dateCreated")
                .addColumnMapping("metadata_id","metadataId")
                .executeAndFetch(Message.class);
            return messages;
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
                    "   date_created real NOT NULL," +
                    "   metadata_id INTEGER " +
                    ")"
            ).executeUpdate();
        }
    }
}
