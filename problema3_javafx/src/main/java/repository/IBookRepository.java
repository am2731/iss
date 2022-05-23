package repository;

import model.Book;
import model.Subscriber;

import java.util.List;

public interface IBookRepository extends Repository<Book, Integer>  {


    List<Book> findAllAvailable();
}
