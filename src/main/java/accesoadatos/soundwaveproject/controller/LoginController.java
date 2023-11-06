package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_registrer;

    @FXML
    private TextField txt_mail;

    @FXML
    private PasswordField txt_password;

    @FXML
    public void setButton() throws IOException {
        App.setRoot("registrer");
    }




}
