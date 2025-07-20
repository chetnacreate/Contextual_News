package com.newsengine.contextualnews.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NearbyResponseDTO {
    private  String title;
    private  String sourceName;
    private  Double latitude;
    private  Double longitude;


}
