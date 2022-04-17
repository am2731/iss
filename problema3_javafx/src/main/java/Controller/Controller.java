package Controller;


import model.*;
import repository.LibrarianRepository;

import repository.SubscriberRepository;

import java.util.List;

public class Controller {

    private  final LibrarianRepository librarianRepository;
    private final SubscriberRepository subscriberRepository;

    private Entity<Integer> currentAdmin;

    public Controller(LibrarianRepository librarianRepository,  SubscriberRepository subscriberRepository){

        this.librarianRepository = librarianRepository;
        this.subscriberRepository = subscriberRepository;

    }



    public void logIn(String email, String password) {

        Librarian librarian = librarianRepository.findByEmail(email);

        Subscriber subscriber = subscriberRepository.findByEmail(email);

        if (librarian.getPassword().equals(password)) {
            currentAdmin = librarian;
        } else if (subscriber.getPassword().equals(password)) {
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




}
