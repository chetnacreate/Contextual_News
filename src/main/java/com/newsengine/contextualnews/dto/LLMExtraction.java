package com.newsengine.contextualnews.dto;

import lombok.Data;

import java.util.List;

@Data
public class LLMExtraction {
    private List<String> entities;
    private String intent;
}
