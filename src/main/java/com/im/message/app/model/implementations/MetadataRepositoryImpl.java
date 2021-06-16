package com.im.message.app.model.implementations;

import com.im.message.app.model.entities.Metadata;
import com.im.message.app.model.repositories.MetadataRepository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class MetadataRepositoryImpl implements MetadataRepository {

    private Sql2o sql2o;

    public MetadataRepositoryImpl() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db",null,null);
        createTable();
    }

    @Override
    public Metadata saveMetadata(Metadata metadata) {
        try (Connection conn = sql2o.open()) {
            Long id = conn.createQuery("insert into metadata" +
                    "(url,mime_type,size, name, date_created, duration) " +
                    "VALUES (:url,:mime_type,:size, :name, :date_created, :duration)")
                    .addParameter("url", metadata.getUrl())
                    .addParameter("mime_type", metadata.getMimeType())
                    .addParameter("size", metadata.getSize())
                    .addParameter("name", metadata.getName())
                    .addParameter("date_created", metadata.getDateCreated())
                    .addParameter("duration", metadata.getDuration())
                    .executeUpdate()
                    .getKey(Long.class);
            metadata.setId(id);
            return metadata;
        }
    }

    private void createTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("CREATE TABLE IF NOT EXISTS metadata (" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "   url text NOT NULL," +
                    "   mime_type text NOT NULL," +
                    "   size INTEGER NOT NULL," +
                    "   name text NOT NULL, " +
                    "   duration INTEGER, " +
                    "   date_created real NOT NULL" +
                    ")"
            ).executeUpdate();
        }
    }
}
