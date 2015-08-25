package com.weblibrary.Servlet.ClientServlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.dao.BookDAOHibernateImpl;
import com.weblibrary.entity.Book;
import com.weblibrary.service.BookFull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/findAll")
public class ServletAllFinder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputData = request.getParameter("bookData");
        System.out.println("input data: " + inputData);

        Gson gson = new Gson();
        JsonObject input = gson.fromJson(inputData, JsonElement.class).getAsJsonObject();

        String  title = input.get("title").getAsString(),
                author = input.get("author").getAsString(),
                year = input.get("year").getAsString(),
                genre = input.get("genre").getAsString();

        BookDAO bookDAO = new BookDAOHibernateImpl();
        BookFull bookFull = bookDAO.findAll(title, author, year, genre);
        List<Book> books = bookFull.getAll();

        response.setContentType("application/json");
        response.getOutputStream().print(gson.toJson(books));
        response.getOutputStream().flush();
    }
}
