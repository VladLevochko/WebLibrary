package com.weblibrary.dao;
import com.weblibrary.entity.Book;
import com.weblibrary.entity.Genre;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class BookDAOHibernateImplTest {
    BookDAO bookDAO = new BookDAOHibernateImpl();
    String genre2 = "Horror";

    @Test
    public void testAddBook()throws HibernateException {

        long i = bookDAO.addBook("t", "a", "1920", "Horror", "", "");

        Book expectedBook1 = new Book("t", "a", "1920");
        expectedBook1.setIsbn(i);
        expectedBook1.getGenres().add(Genre.getGenre(genre2));

        Book expectedBook = new Book("tl", "au", "1920");
        expectedBook.setIsbn(i);
        expectedBook.getGenres().add(Genre.getGenre(genre2));

        Session session=HibernateUtil.beginTransaction();
        Book resultBook = (Book) session.get(Book.class, i);
        HibernateUtil.commitTransaction();

        assertEquals(expectedBook1, resultBook);
        assertFalse(expectedBook.equals(resultBook));

    }

    @Test
    public void testDelete() throws HibernateException{
        long i = bookDAO.addBook("t", "a", "1920", "Horror", "", "");

        Book expectedBook1 = new Book("t", "a", "1920");
        expectedBook1.setIsbn(i);
        expectedBook1.getGenres().add(Genre.getGenre(genre2));

        Session session=HibernateUtil.beginTransaction();

        String hql = "delete Book where isbn= :number";
        Query query  = session.createQuery(hql);
        query.setLong("number", i);
        query.executeUpdate();

        HibernateUtil.commitTransaction();

        session=HibernateUtil.beginTransaction();

        String hql1 = "from Book";
        Query query1  = session.createQuery(hql1);
        ArrayList<Book> list = (ArrayList<Book>) query1.list();

        HibernateUtil.commitTransaction();

        assertFalse(list.contains(expectedBook1));
    }

    @Test(expected = Exception.class)
    public void testDelete1() throws HibernateException{
        Session session=HibernateUtil.beginTransaction();

        String hql = "delete Book where isbn= :number";
        Query query  = session.createQuery(hql);
        query.setLong("number", -1);
        int expected = query.executeUpdate();

        HibernateUtil.commitTransaction();
    }
}
