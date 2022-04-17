package com.example.problema3_javafx;

import Controller.Controller;
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


public class mainProfileController extends AbstractController {

    @FXML
    private Label adminName;


    @FXML
    private Button buttonLogOut;


    protected mainProfileController(Controller controller) {
        super(controller);
    }

    @FXML
    protected void initialize() {
        initializeCurrentUserDetails();

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




}
