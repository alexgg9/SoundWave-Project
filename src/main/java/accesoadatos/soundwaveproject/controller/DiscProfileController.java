package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.DAO.DiscoDAO;
import accesoadatos.soundwaveproject.model.Disco;
import accesoadatos.soundwaveproject.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DiscProfileController {

    @FXML
    private ImageView portadaDisco;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<Cancion> canciones;
    @FXML
    private Label nombreDisco;
    @FXML
    private Label artistaDisco;
    @FXML
    private Label fechaDisco;
    @FXML
    private Label reproduccionesDisco;



    private DiscoDAO discoDAO;

    public void initialize() {
        discoDAO = new DiscoDAO();
    }

    @FXML
    public void btSearch() throws SQLException {
        searchDisco();
    }

    @FXML
    public void back() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void searchDisco() throws SQLException {
        String search = searchField.getText();
        Disco disco = discoDAO.getDiscoByNombre(search);

        if (disco != null && disco.getId() != 0) {
            System.out.println("ID: " + disco.getId());
            nombreDisco.setText("Nombre: " + disco.getNombre());
            artistaDisco.setText("Artista: " + disco.getArtista().getNombre());
            fechaDisco.setText("Fecha Publicación: " + disco.getFechaPublicacion());
            reproduccionesDisco.setText("Reproducciones: " + disco.getReproduccion());
            Image image = Utils.convertBytesToArray(disco.getFoto());
            portadaDisco.setImage(image);

            List<Cancion> songs = discoDAO.getCancionesByDiscoId(disco.getId());
            canciones.getItems().clear();
            canciones.getItems().setAll(songs);
        } else {
            Utils.showPopUp("Disco", "Error", "No se encontró en la base de datos", Alert.AlertType.ERROR);
        }
    }
}