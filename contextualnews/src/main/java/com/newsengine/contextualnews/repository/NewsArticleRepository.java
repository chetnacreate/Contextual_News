package com.newsengine.contextualnews.repository;

import com.newsengine.contextualnews.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsArticleRepository extends JpaRepository<NewsArticle, String>, NewsArticleRepositoryCustom  {
    // Find by category (case-insensitive)
    @Query("SELECT n FROM NewsArticle n WHERE :category MEMBER OF n.category ORDER BY n.publicationDate DESC")
    List<NewsArticle> findByCategoryIgnoreCaseOrderByPublicationDateDesc(@Param("category") String category);

    // Find by source name (case-insensitive)
    List<NewsArticle> findBySourceNameIgnoreCaseOrderByPublicationDateDesc(String sourceName);

    // Find by relevance score greater than a threshold
    List<NewsArticle> findByRelevanceScoreGreaterThanOrderByRelevanceScoreDesc(Double relevanceScore);

    // Search by title or description containing keyword (case-insensitive)
    List<NewsArticle> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String titleKeyword, String descriptionKeyword
    );

    @Query("""
  SELECT n FROM NewsArticle n
  WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :kw, '%'))
     OR LOWER(n.description) LIKE LOWER(CONCAT('%', :kw, '%'))
""")
    List<NewsArticle> searchByKeyword(@Param("kw") String keyword);


}
