package com.weblibrary.service;
import com.weblibrary.entity.Book;

import java.util.*;

public class BookFull {
    private List<Book> books;

    @Override
    public String toString() {
        return "BookFull{" +
                "books=" + books +
                '}';
    }

    public BookFull(List<Book> books){
        this.books=books;
    }

    public Book getRandom(){
        Random random = new Random();
        int number = random.nextInt(books.size());
        Book book = books.get(number);
        books.remove(number);
        System.out.println(number + " " + books.size());
        return book;
    }

    public List<Book> getAll(){
        return books;
    }
}