package com.newsengine.contextualnews.service;

import com.newsengine.contextualnews.dto.NearbyResponseDTO;
import com.newsengine.contextualnews.model.NewsArticle;
import com.newsengine.contextualnews.repository.NewsArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private  final NewsArticleRepository newsArticleRepository;

    public List<NearbyResponseDTO> getNearbyArticle(double lat, double lon, double radius){
        List<NewsArticle> nearby = newsArticleRepository.findNearby(lat,lon,radius);
        return  nearby.stream()
                .map(article ->  new NearbyResponseDTO(
                      article.getTitle(),
                        article.getSourceName(),
                      article.getLatitude(),
                      article.getLongitude()

                )).collect(Collectors.toList());
    }

    public List<NearbyResponseDTO> searchArticles(String keyword){
        List<NewsArticle> list = newsArticleRepository.searchByKeyword(keyword);

        return list.stream()
                .map(a -> new NearbyResponseDTO(
                        a.getTitle(),
                        a.getSourceName(),
                        a.getLatitude(),
                        a.getLongitude()
                )).collect(Collectors.toList());
    }

    public List<NearbyResponseDTO> getByCategory(String category){
        List<NewsArticle> list = newsArticleRepository.findByCategoryIgnoreCaseOrderByPublicationDateDesc(category);

        return  list.stream()
                .map(a ->  new NearbyResponseDTO(
                        a.getTitle(),
                        a.getSourceName(),
                        a.getLatitude(),
                        a.getLongitude()
                )).collect(Collectors.toList());
    }

    public List<NearbyResponseDTO> getByScore(Double relevanceScore){
        List<NewsArticle> list = newsArticleRepository.findByRelevanceScoreGreaterThanOrderByRelevanceScoreDesc(relevanceScore);
        return  list.stream()
                .map(a-> new NearbyResponseDTO(
                        a.getTitle(),
                        a.getSourceName(),
                        a.getLatitude(),
                        a.getLongitude()
                )).collect(Collectors.toList());
    }

    public  List <NearbyResponseDTO> getBySource( String sourceName){
        List<NewsArticle> list = newsArticleRepository.findBySourceNameIgnoreCaseOrderByPublicationDateDesc(sourceName);

        return list.stream()
                .map(a -> new NearbyResponseDTO(
                        a.getTitle(),
                        a.getSourceName(),
                        a.getLatitude(),
                        a.getLongitude()
                )).collect(Collectors.toList());
    }



}
