package com.newsengine.contextualnews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String sourceName;
    private Double latitude;
    private Double longitude;
    private String llmSummary;
}
