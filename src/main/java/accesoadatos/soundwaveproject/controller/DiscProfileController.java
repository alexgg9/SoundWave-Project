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
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DiscProfileController {

    @FXML
    private ImageView back;
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



    public void initialize(){
        DiscoDAO discoDAO = new DiscoDAO();

        try {
            List<Disco> discos = discoDAO.getAll();
            canciones.getItems().addAll((Cancion) discos);
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja la excepción de manera apropiada
        }
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
        DiscoDAO discoDAO = new DiscoDAO();
        Disco disco = discoDAO.getDiscoByNombre(search);

        if(disco != null){
            nombreDisco.setText("Nombre:" + disco.getNombre());
            artistaDisco.setText("Artista: "+ disco.getArtista());
            fechaDisco.setText("Fecha Publicación:"+ disco.getFechaPublicacion());
            reproduccionesDisco.setText("Reproducciones: "+ disco.getReproduccion());
            List<Cancion> songs = discoDAO.getCancionesByDiscoId(disco.getId());

            canciones.getItems().clear();
            canciones.getItems().addAll(songs);

        }else {
            Utils.showPopUp("Disco","Error","No se encontró en la base de datos", Alert.AlertType.ERROR);
        }


    }
}
