package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.model.singleton.UserSession;
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
        UsuarioDAO userDAO = new UsuarioDAO();

        String nombreUsuario = txt_mail.getText(); // El campo txt_mail ahora contiene el nombre de usuario
        String contraseña = txt_password.getText();

        Usuario user = userDAO.getByNombreUsuario(nombreUsuario);

        if (user != null && user.getContraseña().equals(contraseña)) {
            // Las credenciales son válidas, el usuario puede iniciar sesión
            UserSession userSession = UserSession.getInstance();
            userSession.loginUser(user);
            label.setText("Sesión iniciada correctamente");
            label.setTextFill(Color.GREEN);

            // Redirige al usuario a la siguiente pantalla
            App.setRoot("registrer");
        } else {
            // Credenciales incorrectas, muestra un mensaje de error
            label.setText("Nombre de usuario o contraseña incorrectos");
            label.setTextFill(Color.RED);
        }
    }



}
