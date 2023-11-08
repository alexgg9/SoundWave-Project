package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.model.singleton.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Arrays;

public class UserProfileController {

    @FXML
    private Label nombreUsuario;
    @FXML
    private Button botonDatos;

    @FXML
    private ListView<Lista> listViewUser;
    @FXML
    private ImageView fotoUsuario;

    @FXML
    public void back() throws IOException {
        App.setRoot("home");
    }

    public void initialize() {

        Usuario usuario = UserSession.getInstance().getUsuarioActual();

        if (usuario != null) {
            nombreUsuario.setText(usuario.getNombre());
            Image image = new Image(Arrays.toString(usuario.getFoto()));
            fotoUsuario.setImage(image);
            listViewUser.getItems().addAll(usuario.getMisListas());
        }
    }
}
