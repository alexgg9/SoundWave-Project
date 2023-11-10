package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.DAO.ListaDAO;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.model.singleton.UserSession;
import accesoadatos.soundwaveproject.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserProfileController {

    @FXML
    private Label nombreUsuario;
    @FXML
    private Label correoUsuario;
    @FXML
    private Button botonDatos;

    @FXML
    private ListView<Lista> listViewUser;
    @FXML
    private ImageView fotoUsuario;

    private ListaDAO listaDAO;

    @FXML
    public void back() throws IOException {
        App.setRoot("home");
    }

    public void initialize() {
        Usuario usuario = UserSession.getInstance().getUsuarioActual();
        listaDAO = new ListaDAO();
        if (usuario != null) {
            nombreUsuario.setText(usuario.getNombre());
            correoUsuario.setText(usuario.getCorreo());
            Image image = Utils.convertBytesToArray(usuario.getFoto());
            fotoUsuario.setImage(image);
            List<Lista> userList = ListaDAO.getListasByUsuario(usuario.getDni());
            listViewUser.getItems().setAll(userList);
        } else {
            System.out.println("La lista de misListas es null.");
        }
    }
}

