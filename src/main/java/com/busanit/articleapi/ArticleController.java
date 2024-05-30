package com.busanit.articleapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // INSERT
    @PostMapping
    public ResponseEntity<Article> createArticle (@RequestBody Article article) {
        Article createArticle = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(createArticle);
    }

    // SELECT (전체 데이터)
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // SELECT (일부)
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById (@PathVariable Long id) {
        Article article = articleService.getArticleById(id);

        if(article == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(article);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updateArticle) {
        Article article = articleService.updateArticle(id, updateArticle);
        if(article == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(article);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean isDeletedArticle = articleService.DeleteArticle(id);
        if(!isDeletedArticle) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().build();
        }
    }
}
