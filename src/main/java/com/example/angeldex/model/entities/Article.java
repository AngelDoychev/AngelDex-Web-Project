package com.example.angeldex.model.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "articles")
public class Article extends BaseEntity {
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    private UserEntity user;

    @Column
    private boolean isConfirmed;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;


    public Article() {
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
