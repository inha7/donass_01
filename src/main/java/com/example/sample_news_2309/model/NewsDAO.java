package com.example.sample_news_2309.model;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.N;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class NewsDAO {
    private final String jdbcDriver = "org.mariadb.jdbc.Driver";
    private final String jdbcUrl = "jdbc:mariadb://localhost:3306/inha";
    private final String jdbcUser = "root";
    private final String jdbcPassword = "7894";

    public Connection open() {
        // db 연결을 위한 메서드
        // 각각의 메서드마다 연결을 만들고 해제하는 구조
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return connection;
    }

    public void insertNews(News news) throws SQLException {
        // 뉴스를 추가하는 메서드'
        @Cleanup Connection connection = open();
        String sql = "INSERT INTO news (title,img,date,content) VALUES (?,?,now(),?)";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, news.getTitle());
        preparedStatement.setString(2, news.getImg());
        preparedStatement.setString(3, news.getContent());
        preparedStatement.executeUpdate();

    }

    public List<News> selectAll() throws SQLException {
        // 뉴스기사 목록 전체 가져오기
        @Cleanup Connection connection = open();
        List<News> newsList = new ArrayList<>();

        String sql = "SELECT * FROM news";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            News news = new News();
            news.setAid(resultSet.getInt("aid"));
            news.setTitle(resultSet.getString("title"));
            news.setDate(resultSet.getString("date"));
            news.setContent(resultSet.getString("content"));
            newsList.add(news);
        }
        return newsList;
    }

    public News selectOne(int aid) throws SQLException {
        log.info("selectOne(int aid)...");
        @Cleanup Connection connection = open();
        News news = null;

        String sql = "SELECT * FROM news WHERE aid = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,aid);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            news = new News();
            news.setAid(resultSet.getInt("aid"));
            news.setTitle(resultSet.getString("title"));
            news.setImg(resultSet.getString("img"));
            news.setDate(resultSet.getString("date"));
            news.setContent(resultSet.getString("content"));
        }
        return news;
    }

    public void deleteNews(int aid) throws SQLException {
        // 뉴스 삭제
        @Cleanup Connection connection = open();

        String sql = "DELETE FROM news WHERE aid=?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, aid);
        if (preparedStatement.executeUpdate() == 0) {
            throw new SQLException("DB에러");
        }

    }


}
