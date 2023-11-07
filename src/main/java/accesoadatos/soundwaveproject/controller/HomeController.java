package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class HomeController {

    @FXML
    private TextField searchTextField;

    @FXML
    private AnchorPane sideMenu;

    @FXML
    private ImageView userImageView;

    @FXML
    private Text userNameText;

    @FXML
    private GridPane popularDiscsGrid;

    @FXML
    private GridPane popularArtistsGrid;

    public void initialize() {
        // Aquí puedes agregar la lógica de inicialización de la vista.
        // Por ejemplo, cargar datos, configurar eventos, etc.
        loadHomeView();
    }

    // Puedes agregar métodos para manejar eventos, acciones, etc.
    // Por ejemplo, para manejar la búsqueda cuando el usuario presiona Enter en el campo de búsqueda.
    @FXML
    private void handleSearchAction() {
        String searchTerm = searchTextField.getText();
        // Realiza la búsqueda y muestra los resultados.
    }

    // Otros métodos relacionados con la interacción del usuario.

    @FXML
    private void loadHomeView() {
        // Carga y muestra la vista 'Home' en el área de contenido principal.
        // Puedes cargar la vista utilizando FXMLLoader y establecerla en mainContentArea.
    }

    @FXML
    private void loadDiscsView() throws IOException {
        // Carga y muestra la vista 'Discs' en el área de contenido principal.
        App.setRoot("discProfile");
    }

    @FXML
    private void loadArtistsView() throws IOException {
        // Carga y muestra la vista 'Artists' en el área de contenido principal.
        App.setRoot("artistProfile");
    }

    @FXML
    private void loadListsView() throws IOException {
        // Carga y muestra la vista 'Lists' en el área de contenido principal.
        App.setRoot("list");
    }

    @FXML
    private void loadProfileView() throws IOException {
        // Carga y muestra la vista de perfil en el área de contenido principal.
        App.setRoot("userProfile");
    }

}
