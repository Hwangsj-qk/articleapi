package com.busanit.articleapi;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    // 조회(모든 기사)
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    // 조회 (일부 기사)
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // 생성
    @Transactional
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    // 업데이트
    @Transactional
    public Article updateArticle(Long id, Article updateArticle) {
        Article article = articleRepository.findById(id).orElse(null);
        if(article != null) {
            if(updateArticle.getTitle() != null) {
                article.setTitle(updateArticle.getTitle());
            }
            if(updateArticle.getContent() != null) {
                article.setContent(updateArticle.getContent());
            }
            if(updateArticle.getAuthor() != null) {
                article.setAuthor(updateArticle.getAuthor());
            }
            return articleRepository.save(article);
        } else {
            return null;
        }
    }

    // 삭제
    @Transactional
    public Boolean DeleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if(article != null) {
            articleRepository.delete(article);
            return true;
        } else {
            return false;
        }
    }

}
