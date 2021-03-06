package com.example.problema3_javafx;

import Controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Book;
import repository.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Properties props=new Properties();
        try {
           // props.load(new FileReader("problema3_javafx/bd.config"));
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }


        LibrarianRepository librarianRepository = new LibrarianRepository(props);
        SubscriberRepository subscriberRepository = new SubscriberRepository(props);
        BookRepository bookRepository = new BookRepository(props);
        BooksSubscribersRepository bs = new BooksSubscribersRepository(props);

        Iterable<Book> books = bookRepository.findAll();
        List<Book> books2 = bookRepository.findAllAvailable();
        Controller controller = new Controller( librarianRepository, subscriberRepository, bookRepository, bs);

        Stage firstStage = new Stage();
        ScreenController.switchScene(firstStage, "login-view.fxml", new LoginController(controller));



    }

    public static void main(String[] args) {

        launch();
    }
}