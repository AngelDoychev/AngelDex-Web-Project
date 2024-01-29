package com.example.angeldex.model.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SearchArticlesDto {
    @NotEmpty(message = "Cannot search for nothing.")
    @Size(min = 3, max = 10, message = "Size must be between 3 and 10.")
    public String word;

    public SearchArticlesDto() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
