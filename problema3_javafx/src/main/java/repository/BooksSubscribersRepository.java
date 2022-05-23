package repository;

import model.Book;
import model.BooksSubscribers;
import model.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BooksSubscribersRepository implements IBooksSubscribersRepository {

    private static final Logger logger= LogManager.getLogger();
    private JdbcUtils dbUtils;

    public BooksSubscribersRepository(Properties props) {

        dbUtils = new JdbcUtils(props);
    }


    @Override
    public void add(BooksSubscribers elem) {
        logger.traceEntry("saving book {}", elem);
        try {
            Hibernater.initialize();
            try (Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    BooksSubscribers book = new BooksSubscribers(elem.getIdBook(), elem.getIdSubscriber());
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
    public Iterable<BooksSubscribers> findAll() {
        logger.traceEntry();
        List<BooksSubscribers> books=new ArrayList<>();
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    books =
                            session.createQuery("from BooksSubscribers", BooksSubscribers.class).list();

                    System.out.println(books.size() + "bs found:");

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
                    BooksSubscribers book= session.createQuery("from BooksSubscribers where book_id="+id, BooksSubscribers.class).setMaxResults(1)
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
    public void update(BooksSubscribers elem, Integer id) {

    }

    @Override
    public BooksSubscribers findById(Integer id) {
        return null;
    }
}
