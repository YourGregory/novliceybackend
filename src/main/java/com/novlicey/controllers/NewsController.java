package com.novlicey.controllers;

import com.novlicey.exception.ResourceNotFoundException;
import com.novlicey.models.News;
import com.novlicey.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable(value = "id") Long newsId)
            throws ResourceNotFoundException {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + newsId));
        return ResponseEntity.ok().body(news);
    }

    @PostMapping("/news")
    public News createNews(@Valid @RequestBody News news) {
        return newsRepository.save(news);
    }

    @PostMapping("/news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable(value = "id") Long newsId,
                                           @Valid @RequestBody News newsDetails) throws ResourceNotFoundException {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + newsId));

        news.setTitle(newsDetails.getTitle());
        news.setText(newsDetails.getText());
        final News updatedNews = newsRepository.save(news);

        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/news/{id}")
    public Map<String, Boolean> deleteNews(@PathVariable(value = "id") Long newsId)
            throws ResourceNotFoundException {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + newsId));

        newsRepository.delete(news);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
