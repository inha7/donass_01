package dao;

import com.example.sample_news_2309.model.News;
import com.example.sample_news_2309.model.NewsDAO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Log4j2
public class ConnectionTests {

    @Test
    public void testOpen() {
        NewsDAO newsDAO = new NewsDAO();
        Connection connection = newsDAO.open();
        log.info(connection);
    }

//    private int aid;
//    private String title;
//    private String img;
//    private Date date;
//    private String content;
    NewsDAO newsDAO = new NewsDAO();
    @Test
    public void testInsert() throws SQLException {


        for (int i = 1; i <= 10; i++) {
            News news = new News();
            news.setTitle("제목"+i);
            news.setImg("이미지"+i);
            news.setContent("내용"+i);

            newsDAO.insertNews(news);
        }
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<News> newsList = newsDAO.selectAll();

        // newsList.forEach(news -> System.out.println(news));

        for (News news : newsList) {
            log.info(news);
        }
    }

    @Test
    public void testSelectOne() throws Exception {
        News news = newsDAO.selectOne(2);
        log.info(news);
    }

    @Test
    public void testDelete() throws Exception {
        int aid=9;
        newsDAO.deleteNews(aid);
    }
}
