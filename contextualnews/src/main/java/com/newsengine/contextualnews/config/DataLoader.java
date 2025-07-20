package com.newsengine.contextualnews.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.newsengine.contextualnews.model.NewsArticle;
import com.newsengine.contextualnews.repository.NewsArticleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final NewsArticleRepository newsArticleRepository;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @PostConstruct
    public void loadData(){
        try{
            InputStream inputStream = new ClassPathResource("data/news_data.json").getInputStream();  //ClassPathResource loads the JSON file from resources/
            List<NewsArticle> articles = objectMapper.readValue(inputStream, new TypeReference<List<NewsArticle>>() {   //ObjectMapper deserializes the JSON array into a List<NewsArticle>
            });

            for(NewsArticle article: articles){
                if(article.getPublicationDate()==null && article.getUrl()!=null){
                    try{
                        LocalDateTime parsedDate = LocalDateTime.parse(article.getPublicationDate().toString(), DateTimeFormatter.ISO_DATE_TIME);
                        article.setPublicationDate(parsedDate);
                    }catch (Exception e){
                        System.out.println("Error parsing date: "+ article.getTitle());
                    }
                }
            }
            newsArticleRepository.saveAll(articles);
            System.out.println("Loaded" + articles.stream()+"artcles into DB");

        } catch (Exception e) {
            System.err.println("Failed to load json:"+e.getMessage());
        }
    }
}
