package com.example.angeldex.service;

import com.example.angeldex.model.entities.Article;
import com.example.angeldex.model.entities.UserEntity;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Optional<Article> findArticleById(long id);
    Article updateArticle(Article article);
    Article createArticle(String title, String content, UserEntity user, String thumbnail, boolean isConfirmed);
    void deleteArticleByID(long id);
    List<Article> findAllArticles();
    Optional<Article> findByTitle(String title);
    List<Article> findAllByUserId(Long id);
    Article save(Article article);

    List<Article> findAllArticlesThatIncludeWord(String word);
}
