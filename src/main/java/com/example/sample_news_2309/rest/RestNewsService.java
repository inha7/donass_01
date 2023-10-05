package com.example.sample_news_2309.rest;

import com.example.sample_news_2309.model.News;
import com.example.sample_news_2309.model.NewsDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/news")
public class RestNewsService {
    NewsDAO dao;

    public RestNewsService() {
        dao = new NewsDAO();
    }

    //등록
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // 클라이언트 요청에 포함된 미디어타입을 지정. JSON 사용
    public String addNews(News news) {
        // 뉴스 등록 - post방식으로 들어오면 실행
        try {
            dao.insertNews(news);
            // @Consumes 설정에 따라 HTTP Body에 포함된 JSON 문자열이 자동으로 News 로 변환.
            // 이를 위해서 JSON 문자열의 키와 News 객체의 멤버변수 명이 동일해야 함.
        } catch (Exception e) {
            e.printStackTrace();
            return "News API : 뉴스 등록 실패";
        }
        return "News API : 뉴스 등록됨!";
    }

    @GET
    @Path("{aid}") /* /api/news/100 의 형태로 요청을 하면 100이 aid의 값으로 자동 처리됨. */
    @Produces(MediaType.APPLICATION_JSON)
    public News getNews(@PathParam("aid") int aid) {
        News news = null;
        try {
            news = dao.selectOne(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
        // Produces 설정으로 News객체가 JSON 문자열로 출력
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNews(){
        List<News> newsList = null;
        try {
            newsList = dao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    @DELETE
    @Path("{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteNews(@PathParam("aid") int aid) {
        try {
            dao.deleteNews(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aid + "번 기사 삭제완료!";
    }



}
