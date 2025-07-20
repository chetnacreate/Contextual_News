package com.newsengine.contextualnews.repository;

import com.newsengine.contextualnews.model.NewsArticle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsArticleRepositoryImpl implements NewsArticleRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsArticle> findNearby(double latitude, double longitude, double radiusKm){
        String sql = """
        SELECT * FROM news_article
        WHERE (6371 * acos(
            cos(radians(:lat)) * cos(radians(latitude)) *
            cos(radians(longitude) - radians(:lon)) +
            sin(radians(:lat)) * sin(radians(latitude))
        )) < :radius
        ORDER BY (6371 * acos(
            cos(radians(:lat)) * cos(radians(latitude)) *
            cos(radians(longitude) - radians(:lon)) +
            sin(radians(:lat)) * sin(radians(latitude))
        )) ASC
        LIMIT 5
    """;
        return entityManager.createNativeQuery(sql, NewsArticle.class)
                .setParameter("lat", latitude)
                .setParameter("lon", longitude)
                .setParameter("radius", radiusKm)
                .getResultList();
    }
}
