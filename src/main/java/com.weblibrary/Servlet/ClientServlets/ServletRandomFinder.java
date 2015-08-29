package com.weblibrary.Servlet.ClientServlets;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.dao.BookDAOHibernateImpl;
import com.weblibrary.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findRandom")
public class ServletRandomFinder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletRandomFinder() {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputData = request.getParameter("bookData");
        System.out.println("input data: " + inputData);

        Gson gson = new Gson();
        JsonObject input = gson.fromJson(inputData, JsonElement.class).getAsJsonObject();

        BookDAO bookDAO = new BookDAOHibernateImpl();
        Book book = bookDAO.findBook(input.get("genre1").getAsString());

        response.setContentType("application/json");
        response.getOutputStream().print(gson.toJson(book));
        response.getOutputStream().flush();
    }
}
