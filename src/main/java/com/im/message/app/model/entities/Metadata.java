package com.im.message.app.model.entities;

import java.util.Date;

public class Metadata {

    private Long id;
    private String url;
    private String mimeType;
    private Long size;
    private String name;
    private Date dateCreated;
    private Long duration; //for video duration in seconds

    public Metadata(String url, String mimeType, Long size, String name, Date dateCreated) {
        this.url = url;
        this.mimeType = mimeType;
        this.size = size;
        this.name = name;
        this.dateCreated = dateCreated;
    }
    public Metadata(String url, String mimeType, Long size, String name, Date dateCreated,Long duration) {
        this.url = url;
        this.mimeType = mimeType;
        this.size = size;
        this.name = name;
        this.dateCreated = dateCreated;
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Long getDuration() {
        return duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
