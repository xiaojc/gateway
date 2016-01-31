package com.ohayoyo.gateway.test;

import java.io.Serializable;

public class TestImage implements Serializable {

    private String hottime ;

    private String title;

    private String description;

    private String picUrl;

    private String url;

    public String getHottime() {
        return hottime;
    }

    public TestImage setHottime(String hottime) {
        this.hottime = hottime;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TestImage setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TestImage setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public TestImage setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TestImage setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return "TestImage{" +
                "hottime='" + hottime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
