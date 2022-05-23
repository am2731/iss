package repository;


import model.Book;
import model.JdbcUtils;
import model.Librarian;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.Errors.RepositoryError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookRepository implements IBookRepository {

    private static final Logger logger= LogManager.getLogger();
    private JdbcUtils dbUtils;

    public BookRepository(Properties props) {

        dbUtils = new JdbcUtils(props);
    }


    @Override
    public void add(Book elem) {
        logger.traceEntry("saving book {}", elem);
        try {
            Hibernater.initialize();
            try (Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    Book book = new Book(elem.getTitle(), elem.getAuthor());
                    session.save(book);
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        }catch (Exception ex){
            logger.error(ex);
            System.err.println("Error DB"+ex);
        }finally {
            Hibernater.close();
            logger.traceExit();

        }
    }

    @Override
    public Iterable<Book> findAll() {
        logger.traceEntry();
        List<Book> books=new ArrayList<>();
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    books =
                            session.createQuery("from Book", Book.class).list();

                    System.out.println(books.size() + "books found:");
                    for (Book m : books) {
                        System.out.println(m.getAuthor() + ' ' + m.getTitle() + ' ' + m.getId());
                    }
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        }catch (Exception ex){
            logger.error(ex);
            System.err.println("Error DB"+ex);
        }
        finally {
            Hibernater.close();
            logger.traceExit();
        }
        return books;
    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry();
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    Book book= session.createQuery("from Book where id="+id, Book.class).setMaxResults(1)
                            .uniqueResult();
                    System.err.println("Stergem book" + book.getId());
                    session.delete(book);
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        }catch (Exception ex){
            logger.error(ex);
            System.err.println("Error DB"+ex);
        }
        finally {
            Hibernater.close();
            logger.traceExit();
        }


    }

    @Override
    public void update(Book elem, Integer id) {
        logger.traceEntry("update book {}", elem);
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()){
                Transaction tx=null;
                try{
                    tx = session.beginTransaction();
                    Book book = session.load(Book.class, id);

                    book.setAuthor(elem.getAuthor());
                    book.setTitle(elem.getTitle());
                    book.setStatus(elem.getStatus());

                    session.update(book);
                    tx.commit();
                } catch(RuntimeException ex){
                    if (tx!=null)
                        tx.rollback();
                }
            }
        }catch (Exception ex){
            logger.error(ex);
            System.err.println("Error DB"+ex);
        }
        finally {
            Hibernater.close();
            logger.traceExit();
        }

    }

    @Override
    public Book findById(Integer id) {
        logger.traceEntry();
        Book book = null;
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    book =
                            session.createQuery("from Book where id="+id, Book.class).getSingleResult();

                    System.out.println("book found:"+book);
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        }catch (Exception ex){
            logger.error(ex);
            System.err.println("Error DB"+ex);
        }
        finally {
            Hibernater.close();
            logger.traceExit();
        }
        return book;
    }


    @Override
    public List<Book> findAllAvailable() {
        logger.traceEntry();
        List<Book> books=new ArrayList<>();
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {


                    session.beginTransaction();
                    Query query = session.createQuery("from Book where status = :status_");
                    String status = "AVAILABLE";
                    query.setParameter("status_", status);
                    books = query.getResultList();
                    session.getTransaction().commit();

                    System.out.println(books.size() + "books found:");
                    for (Book m : books) {
                        System.out.println(m.getAuthor() + ' ' + m.getTitle() + ' ' + m.getId());
                    }
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        }catch (Exception ex){
            logger.error(ex);
            System.err.println("Error DB"+ex);
        }
        finally {
            Hibernater.close();
            logger.traceExit();
        }
        return books;
    }
}
