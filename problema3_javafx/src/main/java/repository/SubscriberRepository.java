package repository;


import model.JdbcUtils;
import model.Librarian;
import model.Subscriber;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.Errors.RepositoryError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SubscriberRepository implements ISubscriberRepository{
    private JdbcUtils dbUtils;

    public SubscriberRepository(Properties props) {

        dbUtils=new JdbcUtils(props);
    }


    @Override
    public Subscriber findByEmail(String given_email) {
        /*
        Connection con=dbUtils.getConnection();
        Subscriber subscriber = null;
        try(PreparedStatement preStmt = con.prepareStatement("select * from Subscribers where email = ?")){
            preStmt.setString(1,given_email);
            try(ResultSet result = preStmt.executeQuery()){

                if(result.next()) {
                    String firstName = result.getString("firstname");
                    String lastName = result.getString("lastname");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    int id = result.getInt("id");

                    subscriber = new Subscriber(firstName, lastName, email, password);
                    subscriber.setId(id);
                }



            }
        } catch (SQLException e) {

            System.err.println("Erorr DB"+e);
            throw new RepositoryError("SQL exception!");
        }


        return subscriber;

         */
        Subscriber subscriber = null;
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {


                    session.beginTransaction();
                    Query query = session.createQuery("from Subscriber where email  = :email_");

                    query.setParameter("email_", given_email);
                    subscriber = (Subscriber) query.getSingleResult();
                    session.getTransaction().commit();

                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        }catch (Exception ex){

            System.err.println("Error DB"+ex);
        }
        finally {
            Hibernater.close();

        }
        return subscriber;
    }

    @Override
    public void add(Subscriber elem) {

    }

    @Override
    public Iterable<Subscriber> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Subscriber elem, Integer id) {

    }

    @Override
    public Subscriber findById(Integer id) {
        return null;
    }
}
