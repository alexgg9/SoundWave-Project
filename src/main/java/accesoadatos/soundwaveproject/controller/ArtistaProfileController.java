package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.DAO.ArtistaDAO;
import accesoadatos.soundwaveproject.model.DAO.DiscoDAO;
import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Disco;
import accesoadatos.soundwaveproject.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ArtistaProfileController {
    @FXML
    private ImageView portadaArtista;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<Disco> Discos;
    @FXML
    private Label nombreArtista;
    @FXML
    private Label dniArtista;
    @FXML
    private Label nacionalidadArtista;
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    @FXML
    public void btSearch() throws SQLException {
        searchArtista();
    }

    @FXML
    private void searchArtista() throws SQLException {
        String search = searchField.getText();
        ArtistaDAO artistaDAO = new ArtistaDAO();
        Artista artista = artistaDAO.findByNombre(search);

        if(artista != null){
            nombreArtista.setText("Nombre:" + artista.getNombre());
            dniArtista.setText("DNI: "+ artista.getDni());
            nacionalidadArtista.setText("Nacionalidad:"+ artista.getNacionalidad());
            List<Disco> Disc = artistaDAO.getDiscosByArtista(artista);

            Discos.getItems().clear();
            Discos.getItems().addAll(Disc);

        }else {
            Utils.showPopUp("Artista","Error","No se encontró en la base de datos", Alert.AlertType.ERROR);
        }


    }
}


