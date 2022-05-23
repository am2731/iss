package Controller;


import Utils.Observer.Observable;
import Utils.Observer.Observer;
import model.*;
import repository.BookRepository;
import repository.BooksSubscribersRepository;
import repository.LibrarianRepository;

import repository.SubscriberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller implements Observable {

    private  final LibrarianRepository librarianRepository;
    private final SubscriberRepository subscriberRepository;
    private  final BookRepository bookRepository;
    private  final BooksSubscribersRepository bsRepo;
    private Entity<Integer> currentAdmin;
    private final Set<Observer> observers = new HashSet<>();

    public Controller(LibrarianRepository librarianRepository, SubscriberRepository subscriberRepository, BookRepository bookRepository, BooksSubscribersRepository bsRepo){

        this.librarianRepository = librarianRepository;
        this.subscriberRepository = subscriberRepository;
        this.bookRepository = bookRepository;
        this.bsRepo = bsRepo;

    }



    public void logIn(String email, String password) {

        Librarian librarian = librarianRepository.findByEmail(email);

        Subscriber subscriber = subscriberRepository.findByEmail(email);

        if ( librarian != null && librarian.getPassword().equals(password)) {
            currentAdmin = librarian;
        } else if (subscriber!= null && subscriber.getPassword().equals(password)) {
            currentAdmin = subscriber;
        } else{
            throw new ControllerException("Invalid credentials!");
        }
    }

    public Entity<Integer> getCurrentAdmin() {
        return currentAdmin;
    }

    public void logOut() {
        currentAdmin = null;

    }


    public List<Book> getBooks() {
        return bookRepository.findAllAvailable();
    }

    public void borrowBooks(Set<Book> booksToBorrow) {
        booksToBorrow.forEach(book -> {
            book.setStatus(Status.BORROWED.name());

            bookRepository.update(book, book.getIdd());
            bsRepo.add(new BooksSubscribers( book.getIdd(), currentAdmin.getId()));

        });
        notifyAllObservers();
    }

    public List<Book> getBorrowedBooks() {
        List<Book> books = new ArrayList<Book>();
        bsRepo.findAll().forEach(bs -> {
            if (bs.getIdSubscriber() == currentAdmin.getId()){
                books.add(bookRepository.findById(bs.getIdBook()));
            }
        });

        return books;
    }

    public void returnBooks(Set<Book> booksToReturn) {
        booksToReturn.forEach(book -> {
            book.setStatus(Status.AVAILABLE.name());

            bookRepository.update(book, book.getIdd());
            bsRepo.delete(book.getIdd());
        });
        notifyAllObservers();
    }

    public List<Book> getAllBooks() {
        var books = bookRepository.findAll();
        List<Book> books2 = new ArrayList<Book>();
        for (Book b : books){
            books2.add(b);
        }
        return books2;
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        bookRepository.add(book);
        notifyAllObservers();
    }

    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }


    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }


    @Override
    public void notifyAllObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
