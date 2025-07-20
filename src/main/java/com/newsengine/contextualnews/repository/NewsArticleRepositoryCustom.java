package com.newsengine.contextualnews.repository;

import com.newsengine.contextualnews.model.NewsArticle;

import java.util.List;

public interface NewsArticleRepositoryCustom {
    List<NewsArticle> findNearby(double latitude, double longitude, double radiusKm);
}
