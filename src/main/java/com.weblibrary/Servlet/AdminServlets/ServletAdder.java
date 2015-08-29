package com.weblibrary.Servlet.AdminServlets;

import com.weblibrary.dao.BookDAO;
import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/add")
public class ServletAdder extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String year = request.getParameter("year");
        String genre1 = request.getParameter("genre1");
        String genre2 = request.getParameter("genre2");
        String genre3 = request.getParameter("genre3");

        BookDAO bookDao=(BookDAO)getServletContext().getAttribute("bookDao");

        System.out.println(title + author + year + genre1 + genre2 + genre3);

        try{
            bookDao.addBook(title,author,year,genre1,genre2,genre3);
        } catch (HibernateException e){
            e.printStackTrace();
            String error = "Error adding book! Book with this ISBN already exist!";
            request.setAttribute("error", error);
            request.setAttribute("forwardTo", "admin/admin.html");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }


        Book book = bookDao.findBook(title);
        String message = "Book added!";
        request.setAttribute("book", book);
        request.setAttribute("msg", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/view.jsp");
        requestDispatcher.forward(request, response);
    }
}
