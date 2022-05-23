package repository;


import model.Book;
import model.JdbcUtils;
import model.Librarian;
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

public class LibrarianRepository implements ILibrarianRepository {

    private JdbcUtils dbUtils;

    public LibrarianRepository(Properties props) {

        dbUtils=new JdbcUtils(props);
    }

    @Override
    public void add(Librarian elem) {

    }

    @Override
    public Iterable<Librarian> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Librarian elem, Integer id) {

    }

    @Override
    public Librarian findById(Integer id) {
        return null;
    }

    @Override
    public Librarian findByEmail(String given_email) {
        /*
        Connection con=dbUtils.getConnection();
        Librarian librarian = null;
        try(PreparedStatement preStmt = con.prepareStatement("select * from Librarians where email = ?")){
            preStmt.setString(1,given_email);
            try(ResultSet result = preStmt.executeQuery()){

                if(result.next()){
                    String firstName= result.getString("firstname");
                    String lastName= result.getString("lastname");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    int id = result.getInt("id");

                    librarian = new Librarian(firstName, lastName, email, password);
                    librarian.setId(id);
                }



            }
        } catch (SQLException e) {

            System.err.println("Erorr DB"+e);
            throw new RepositoryError("SQL exception!");
        }

        return librarian;

         */

        Librarian librarian = null;
        try{
            Hibernater.initialize();
            try(Session session = Hibernater.sessionFactory.openSession()) {
                Transaction tx = null;
                try {


                    session.beginTransaction();
                    Query query = session.createQuery("from Librarian where email  = :email_");

                    query.setParameter("email_", given_email);
                    librarian = (Librarian) query.getSingleResult();
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
        return librarian;
    }
}
