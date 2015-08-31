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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 17.08.15.
 */

@WebServlet("/finder")
public class ServletFind extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputData = request.getParameter("input data");
        System.out.println("input data: " + inputData);

        Gson gson = new Gson();
        JsonObject input = gson.fromJson(inputData, JsonElement.class).getAsJsonObject();

        String  ISBN = input.get("isbn").getAsString();
        long isbn = Integer.parseInt(ISBN);

        BookDAO bookDAO = new BookDAOHibernateImpl();
        Book book = bookDAO.findByIsbn(isbn);


        try{
            response.setContentType("application/json");
            response.getOutputStream().print(gson.toJson(book));
            response.getOutputStream().flush();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}