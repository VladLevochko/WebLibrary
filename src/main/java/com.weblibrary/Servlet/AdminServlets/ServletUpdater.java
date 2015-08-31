package com.weblibrary.Servlet.AdminServlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.weblibrary.dao.BookDAO;
import com.weblibrary.dao.BookDAOHibernateImpl;
import com.weblibrary.entity.Book;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class ServletUpdater extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputData = request.getParameter("input data");
        System.out.println("input data: " + inputData);

        Gson gson = new Gson();
        JsonObject input = gson.fromJson(inputData, JsonElement.class).getAsJsonObject();

        String  ISBN = input.get("isbn").getAsString(),
                title = input.get("title").getAsString(),
                author = input.get("author").getAsString(),
                year = input.get("year").getAsString(),
                genre1 = "", genre2 = "", genre3 = "";
        if (input.get("genre1").getAsString() != null) genre1 = input.get("genre1").getAsString();
        if (input.get("genre2").getAsString() != null) genre2 = input.get("genre2").getAsString();
        if (input.get("genre3").getAsString() != null) genre3 = input.get("genre3").getAsString();
        long isbn = Integer.parseInt(ISBN);

        System.out.println(title + ", " + author + ", " + year + ", " + genre1 + ", " + genre2 + ", " + genre3 + ".");
        BookDAO bookDAO = new BookDAOHibernateImpl();
        Book book = bookDAO.update(isbn, title, author, year, genre1, genre2, genre3);


        try{
            response.setContentType("application/json");
            response.getOutputStream().print(gson.toJson(book));
            response.getOutputStream().flush();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
