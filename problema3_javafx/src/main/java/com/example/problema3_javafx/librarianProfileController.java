package com.example.problema3_javafx;

import Controller.Controller;
import Utils.Observer.Observer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static Utils.Functions.encryptPassword;

@SuppressWarnings("unchecked")


public class librarianProfileController extends AbstractController implements Observer {

    @FXML
    private Label adminName;


    @FXML
    private Button buttonLogOut;

    public TableView<Book> table;

    public TableColumn<Book, String> idColumn;
    public TableColumn authorColumn;
    public TableColumn titleColumn;
    public TableColumn statusColumn;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField authorTextField;




    protected librarianProfileController(Controller controller) {
        super(controller);
        controller.addObserver(this);
    }

    @FXML
    protected void initialize() {
        initializeCurrentUserDetails();
        initialize_();
    }



    private void initializeCurrentUserDetails() {
        try {
            Subscriber subscriber = (Subscriber) controller.getCurrentAdmin();
            String fullNameOfCurrentAdmin = subscriber.getFirstName() + " " + subscriber.getLastName();
            adminName.setText(fullNameOfCurrentAdmin);
        } catch (Exception e) {
            Librarian librarian = (Librarian) controller.getCurrentAdmin();
            String fullNameOfCurrentAdmin = librarian.getFirstName() + " " + librarian.getLastName();
            adminName.setText(fullNameOfCurrentAdmin);
        }

    }

    @FXML
    protected void onLogOutButtonPressed() throws IOException {
        controller.logOut();
        ScreenController.switchScene((Stage) buttonLogOut.getScene().getWindow(),"login-view.fxml", new LoginController(controller));
    }

    public void initialize_(){

        idColumn.setCellValueFactory(new PropertyValueFactory("idd"));
        authorColumn.setCellValueFactory(new PropertyValueFactory("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        statusColumn.setCellValueFactory(new PropertyValueFactory("status"));


        List<Book> books = controller.getAllBooks();
        ObservableList<Book> data = FXCollections.observableArrayList(books);

        table.setItems(data);

    }

    @FXML
    protected void addBook() {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        controller.addBook(title, author);
        initialize_();
    }


    @Override
    public void update() {
        initialize_();
    }
}
