package com.im.message.app.services;

import com.im.message.app.model.entities.Metadata;
import com.im.message.app.model.repositories.MetadataRepository;

import java.util.Date;
import java.util.Optional;

public class MetadataService {

    private MetadataRepository metadataRepository;

    public MetadataService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public Optional<Metadata> getMetadataFromResouce(String contenType, String url) {
        Optional<Metadata> optionalMetadata;
        switch (contenType){
            case "image": optionalMetadata = Optional.of(fetchImageMetadata(url));
                break;
            case "video": optionalMetadata = Optional.of(fetchVideoMetadata(url));
                break;
            default:
                optionalMetadata = Optional.empty();
        }
        return optionalMetadata;
    }

    public Metadata saveMetadata(Metadata metadata){
        return metadataRepository.saveMetadata(metadata);
    }

    private Metadata fetchImageMetadata(String url){
        //TODO: implement fetching logic
        return new Metadata(url,"image/jpg",1024L,"mock.jpg",
                new Date() /* in practice a date in the past*/);
    }
    private Metadata fetchVideoMetadata(String url){
        //TODO: implement fetching logic
        return new Metadata(url,"image/mp4",1024L,"mock.mp4",
                new Date() /* in practice a date in the past*/,120L);

    }


}
