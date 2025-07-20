package com.newsengine.contextualnews.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="news_article")
// JPA mappings with entity and table (table name news_articles
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsArticle {

    @Id   //Assuming each article has a unique id
    private  String id;
    private String title;

    @Column(length = 2000)
    private  String description;

    @Column(length = 1000)
    private String url;

    @Column(name = "publication_date")
    @JsonProperty("publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "source_name")
    @JsonProperty("source_name")
    private  String sourceName;

    @ElementCollection
    private List<String> category;

    @Column(name= "relevenace_score")
    @JsonProperty("relevance_score")
    private  Double relevanceScore;
    private Double latitude;
    private  Double longitude;

    @Column(name = "llm_summary", length = 2000)
    private  String llmSummary; // Setting length 2000 to prevent truncation of description
}
