package com.im.message.app.model.repositories;

import com.im.message.app.model.entities.Metadata;


public interface MetadataRepository {
    Metadata saveMetadata(Metadata metadata);
}
