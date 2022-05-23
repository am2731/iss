package com.example.problema3_javafx;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Book;
import model.Subscriber;

import java.io.IOException;
import java.util.*;
import Utils.Observer.Observer;
@SuppressWarnings("unchecked")


public class subscriberProfileController extends AbstractController implements Observer {

    @FXML
    public Label adminName;


    @FXML
    private Button buttonLogOut;

    public TableView<Book> table;
    public TableView<Book> table2;
    public TableColumn<Book, String> idColumn;
    public TableColumn authorColumn;
    public TableColumn titleColumn;

    public TableColumn<Book, String> idColumn1;
    public TableColumn authorColumn1;
    public TableColumn titleColumn1;

    public TableColumn noCopiesColumn;
    public TextArea textArea;
    public TextArea textArea2;
    public Button borrowBtn;
    private Set<Book> booksToBorrow = new HashSet<>();

    private Set<Book> booksToReturn = new HashSet<>();


    protected subscriberProfileController(Controller controller) {
        super(controller);
        controller.addObserver(this);

    }




    private void initializeCurrentUserDetails() {

        Subscriber subscriber = (Subscriber) controller.getCurrentAdmin();
        String fullNameOfCurrentAdmin = subscriber.getFirstName() + " " + subscriber.getLastName();
        adminName.setText(fullNameOfCurrentAdmin);


    }

    @FXML
    protected void onLogOutButtonPressed() throws IOException {
        controller.logOut();
        ScreenController.switchScene((Stage) buttonLogOut.getScene().getWindow(),"login-view.fxml", new LoginController(controller));
    }

    @FXML
    public void initialize(){
        initializeCurrentUserDetails();
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                Book book = (Book) table.getSelectionModel().getSelectedItem();
                addToBorrow(book);
                table.getSelectionModel().clearSelection();
            }
        });
        table2.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                Book book = (Book) table2.getSelectionModel().getSelectedItem();
                addToReturn(book);
                table2.getSelectionModel().clearSelection();
            }
        });
        initialize_();
    }

    private void addToBorrow(Book book) {
        if (!booksToBorrow.contains(book)) {
            textArea.appendText("id: " + book.getIdd() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + "\n");
            booksToBorrow.add(book);
        }


    }

    private void addToReturn(Book book) {
        if (!booksToReturn.contains(book)) {
            textArea2.appendText("id: " + book.getIdd() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + "\n");
            booksToReturn.add(book);
        }


    }

    public void initialize_(){

        idColumn.setCellValueFactory(new PropertyValueFactory("idd"));
        authorColumn.setCellValueFactory(new PropertyValueFactory("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));

        idColumn1.setCellValueFactory(new PropertyValueFactory("idd"));
        authorColumn1.setCellValueFactory(new PropertyValueFactory("author"));
        titleColumn1.setCellValueFactory(new PropertyValueFactory("title"));

        List<Book> books = controller.getBooks();
        ObservableList<Book> data = FXCollections.observableArrayList(books);
        List<Book> books2 = controller.getBorrowedBooks();
        ObservableList<Book> data2 = FXCollections.observableArrayList(books2);
        table.setItems(data);
        table2.setItems(data2);

    }

    @FXML
    protected void borrow() throws IOException {
        controller.borrowBooks(booksToBorrow);
        textArea.setText("");
        booksToBorrow.clear();
        initialize_();
    }

    @FXML
    protected void returnBook() throws IOException {
        controller.returnBooks(booksToReturn);
        textArea2.setText("");
        booksToReturn.clear();
        initialize_();
    }




    @Override
    public void update() {
        initialize_();
    }
}
