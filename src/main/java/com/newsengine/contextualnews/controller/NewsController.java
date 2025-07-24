package com.newsengine.contextualnews.controller;


import com.newsengine.contextualnews.dto.*;
import com.newsengine.contextualnews.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    @Autowired
    private NewsService newsService;


    @GetMapping("/nearby")
    public List<NearbyResponseDTO> getNearbyArticles(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "10") double radius
    ){
        return  newsService.getNearbyArticle(lat,lon,radius);
    }

    @GetMapping("/search")
    public List<NearbyResponseDTO> searchNews(@RequestParam String keyword){
        return newsService.searchArticles(keyword);
    }

    @GetMapping("/category")
    public List<NearbyResponseDTO> getByCategory(@RequestParam String value){
        return  newsService.getByCategory(value);
    }

    @GetMapping("/score")
    public  List<NearbyResponseDTO> getByScore(@RequestParam Double relevanceScore){
        return newsService.getByScore(relevanceScore);
    }

    @GetMapping("/source")
    public List<NearbyResponseDTO> getBySource(@RequestParam String sourceName){
        return  newsService.getBySource(sourceName);
    }

}
