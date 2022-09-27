package com.xiwang.coolshark.entity;

public class Banner {
    private Integer id;
    private String url;

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
