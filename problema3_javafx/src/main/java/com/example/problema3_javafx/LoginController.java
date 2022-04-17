package com.example.problema3_javafx;
import Controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static Utils.Functions.encryptPassword;

public class LoginController extends AbstractController {

    protected LoginController(Controller controller) {
        super(controller);
    }

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonSignUp;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldEmail;

    @FXML
    protected void onLoginButtonPressed() throws IOException {
        String email = textFieldEmail.getText();
        String password = textFieldPassword.getText();

        try {
            controller.logIn(email, password);
            ScreenController.switchScene((Stage) buttonLogin.getScene().getWindow(), "mainProfile-view.fxml", new mainProfileController(controller));
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }



}