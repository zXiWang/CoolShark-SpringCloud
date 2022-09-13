package com.xiwang.boot07.entity;

public class Image {
    private Integer id;
    private String intro;
    private String urls;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", intro='" + intro + '\'' +
                ", urls='" + urls + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
