package com.example.angeldex.service.Impl;

import com.example.angeldex.model.entities.Article;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.repository.ArticleRepository;
import com.example.angeldex.service.ArticleService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Optional<Article> findArticleById(long id) {
        return this.articleRepository.findById(id);
    }

    @Override
    public Article updateArticle(Article article) {
        return this.articleRepository.save(article);
    }

    @Override
    public Article createArticle(String title, String content, UserEntity user, String thumbnail, boolean isConfirmed) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUser(user);
        article.setConfirmed(isConfirmed);
        article.setThumbnail(thumbnail);

        return this.articleRepository.save(article);
    }

    @Override
    public void deleteArticleByID(long id) {
        this.articleRepository.deleteById(id);
    }

    @Override
    public List<Article> findAllArticles() {
        return this.articleRepository.findAll();
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        return this.articleRepository.findByTitle(title);
    }

    @Override
    public List<Article> findAllByUserId(Long id) {
        return this.articleRepository.findAllByUser_Id(id);
    }

    @Override
    public Article save(Article article) {
        return this.articleRepository.save(article);
    }

    @Override
    public List<Article> findAllArticlesThatIncludeWord(String word) {
        return this.articleRepository.findAllByTitleContainingOrContentContaining(word, word);
    }
}
