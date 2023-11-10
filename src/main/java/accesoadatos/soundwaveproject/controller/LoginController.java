package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.model.singleton.UserSession;
import accesoadatos.soundwaveproject.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label label;
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

    @FXML
    public void login() throws IOException {
        String correo = txt_mail.getText();
        String contraseña = txt_password.getText();

        if (!Utils.isValidEmail(correo)) {
            label.setText("Correo electrónico no válido");
            label.setTextFill(Color.RED);
            return;
        }

        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario user = userDAO.getByCorreo(correo);

        if (user != null && user.getContraseña().equals(Utils.encryptSHA256(contraseña))) {
            UserSession userSession = UserSession.getInstance();
            userSession.loginUser(user);
            label.setText("Sesión iniciada correctamente");
            label.setTextFill(Color.GREEN);
            App.setRoot("home");
        } else {
            label.setText("Nombre de usuario o contraseña incorrectos");
            label.setTextFill(Color.RED);
        }
    }




}
