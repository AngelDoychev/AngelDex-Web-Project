package com.example.angeldex.repository;

import com.example.angeldex.model.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);
    List<Article> findAllByUser_Id(Long id);

    List<Article> findAllByTitleContainingOrContentContaining(String title, String content);
}
