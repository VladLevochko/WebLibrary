package com.weblibrary.dao;

import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.BookFull;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class BookDAOHibernateImpl implements BookDAO {

    public long addBook(String title,String author,String year, String genre1, String genre2,String genre3){

        Book book=new Book(title, author, year);

        if(!"".equals(genre1)) book.getGenres().add(Genre.getGenre(genre1));
        if(!"".equals(genre2)) book.getGenres().add(Genre.getGenre(genre2));
        if(!"".equals(genre3)) book.getGenres().add(Genre.getGenre(genre3));

        Session session = HibernateUtil.beginTransaction();
        session.saveOrUpdate(book);
        HibernateUtil.commitTransaction();
        System.out.println("Transaction successful!!! ");
        return book.getIsbn();
    }

    public BookFull findAll(String title,String author,String year, String genre){
        Session session = HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Book.class);
        if (!title.equals("")) c1.add(Restrictions.eq("title", title));
        if (!author.equals("")) c1.add(Restrictions.eq("author", author));
        if (!year.equals("")) c1.add(Restrictions.eq("year", year));
        if (!genre.equals("")) c1.add(Restrictions.eq("genre", genre));
        List<Book> books = c1.list();

        LinkedHashSet<Book> linkedHashSet = new LinkedHashSet<>(books);
        books = new ArrayList<>(linkedHashSet);
        HibernateUtil.commitTransaction();
        return new BookFull(books);
    }

    public void delete(long isbn){

        Session session=HibernateUtil.beginTransaction();
        String hql = "delete Book where isbn= :number";
        Query query  = session.createQuery(hql);
        query.setLong("number", isbn);
        int row =query.executeUpdate();
        System.out.println(row+ " row were deleted");
        HibernateUtil.commitTransaction();
    }

    public Book findBook(String genre){

        Session session=HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Book.class);
        c1.add(Restrictions.eq("genre", genre));
        List<Book> list= (ArrayList<Book>) c1.list();
        HibernateUtil.commitTransaction();
        return list.get(0);
    }

    public Book findByIsbn(long isbn){
        Session session=HibernateUtil.beginTransaction();
        Book book = (Book) session.get(Book.class, isbn);
        HibernateUtil.commitTransaction();
        return book;
    }

    @Override
    public Book update(long isbn, String author, String title, String year, String genre1, String genre2, String genre3) {
        Book book = findByIsbn(isbn);

        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        if(!"".equals(genre1)) book.getGenres().add(Genre.getGenre(genre1));
        if(!"".equals(genre2)) book.getGenres().add(Genre.getGenre(genre2));
        if(!"".equals(genre3)) book.getGenres().add(Genre.getGenre(genre3));

        Session session = HibernateUtil.beginTransaction();
        session.saveOrUpdate(book);
        HibernateUtil.commitTransaction();
        return book;
    }
}
