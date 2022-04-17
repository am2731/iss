package repository;

import model.Librarian;
import model.Subscriber;

public interface ISubscriberRepository extends Repository<Subscriber, Integer>  {

   Subscriber findByEmail(String given_email);
}
