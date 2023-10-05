package com.example.sample_news_2309.controller;

import com.example.sample_news_2309.HelloServlet;
import com.example.sample_news_2309.service.NewsService;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.N;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("*.news")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, location = "c:/upload")
public class NewsController extends HelloServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsService newsService = new NewsService();

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list": // newList.jsp a
                newsService.listNews(req);
                req.getRequestDispatcher("/WEB-INF/newsList.jsp").forward(req, resp);
                break;
            case "add":
                newsService.addNews(req);
                resp.sendRedirect("list.news?action=list");
                break;
            case "remove":
                newsService.removeNews(req);
                resp.sendRedirect("list.news?action=list");
                break;
            case "view":
                newsService.getNews(req);
                req.getRequestDispatcher("/WEB-INF/newsView.jsp").forward(req,resp);
                break;
        }

    }
}
