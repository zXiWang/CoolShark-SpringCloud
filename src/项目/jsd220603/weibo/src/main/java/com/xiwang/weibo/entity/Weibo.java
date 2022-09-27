package com.xiwang.weibo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Weibo {
    private Integer id;
    private String content;
    private String urls;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date created;
    private Integer userId;
    private String nickname;

    @Override
    public String toString() {
        return "Weibo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", urls='" + urls + '\'' +
                ", created=" + created +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
