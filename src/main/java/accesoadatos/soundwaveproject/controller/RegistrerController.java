package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrerController {

    @FXML
    private Button btnBack;

    @FXML
    private Button createAncout;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label labelCreateUser;

    @FXML
    public void back() throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void create() throws SQLException {
        UsuarioDAO userDAO = new UsuarioDAO();

        // Obtén los datos del usuario desde los campos de entrada en tu interfaz gráfica
        String dni = txtDni.getText();
        String nombre = txtName.getText();
        String correo = txtMail.getText();
        String contraseña = txtPassword.getText();
        // Asumiendo que tienes un método para convertir una imagen a bytes
        byte[] foto = getDefaultUserImage();

        // Crea un nuevo objeto Usuario con los datos ingresados
        Usuario nuevoUsuario = new Usuario(dni, nombre, correo, contraseña, foto);

        // Llama al método `save` del DAO para agregar el nuevo usuario a la base de datos
        try {
            userDAO.save(nuevoUsuario);
            labelCreateUser.setText("Usuario creado correctamente");
            labelCreateUser.setTextFill(Color.GREEN);
        } catch (SQLException e) {
            labelCreateUser.setText("Error al crear el usuario");
            labelCreateUser.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    private byte[] getDefaultUserImage() {
        return new byte[0];
    }
}
