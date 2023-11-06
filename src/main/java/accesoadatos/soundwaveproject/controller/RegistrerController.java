package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrerController {

    @FXML
    private Button btnBack;

    @FXML
    private Button createAncout;

    @FXML
    private TextField txtDni;

    @FXML
    private PasswordField txtMail;

    @FXML
    private PasswordField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void back() throws IOException {
        App.setRoot("login");
    }

}
