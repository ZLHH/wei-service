package com.example.productservice.domain;

import java.time.LocalDateTime;

/**
 * Created by zhanglh on 2018/5/16.
 */
public class Information {

    private Integer id;

    private String title;

    private String information;

    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", information='" + information + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
