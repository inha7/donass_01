package com.example.sample_news_2309.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test") // localhost:8080/api/test
public class RestAPIExample {
    // 동일한 요청에 대해 GET,POST 요청을 구분하여 동작

    @GET // GET 방식
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello API Service";
    }

    // POST의 경우 쿼리 파라미터, 즉 HTML 폼이나 body를 통해 전달되는 name=value 형태의 파라미터를 읽어와
    // 사용하고 있으며 이외에도 /api/test/hello 와 같은 형식의 파라미터도 사용 할 수 있음.
    @POST
    @Path("{msg}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(@PathParam("msg") String msg) {
        return msg+" API Service";
    }

}
