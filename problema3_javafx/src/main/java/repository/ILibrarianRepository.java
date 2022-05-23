package repository;


import model.Librarian;

public interface ILibrarianRepository extends Repository<Librarian, Integer>  {

    Librarian findByEmail(String given_email);
}
