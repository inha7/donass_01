package com.example.sample_news_2309.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data // getter/setter/toString/equals/hashCode 모두 컴파일할때 생성
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private int aid;
    private String title;
    private String img;
    private String date;
    private String content;


}
