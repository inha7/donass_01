package com.example.sample_news_2309.rest;

// JAX-RS 관련 패키지는 꼭 javax.ws.rs.* 을 사용
// import javax.ws.rs.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api") // REST API 서비스 진입점을 의미
public class RestConfig extends Application {
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();

        // 클래스에서 REST-API 서비스를 찾는다는 설정으로 속성 이름인
        // "jersey.config.server.provider.packages", "restAPI" 정확히 작성
        properties.put("jersey.config.server.provider.packages", "restAPI");
        return properties;

    }
}
