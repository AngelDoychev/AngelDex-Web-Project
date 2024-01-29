package com.example.angeldex.model.dtos;

import com.example.angeldex.model.entities.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class ArticleBindingDto {

    @NotEmpty(message = "Title cannot be empty.")
    @Size(min = 5, max = 30, message = "Title must be more than 5 character long and less than 30 characters.")
    public String title;
    @NotEmpty(message = "Content cannot be empty.")
    @Size(min = 20, max = 500, message = "Content must be more than 20 character long and less than 500 characters.")
    public String content;

    public UserEntity user;

    public String thumbnail;
    public boolean isConfirmed;


    public ArticleBindingDto() {
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

    public ArticleBindingDto(UserEntity user) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
