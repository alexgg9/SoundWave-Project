package accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Disco;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private HBox fila;

    public void initialize() throws IOException {
        // Aquí puedes agregar la lógica de inicialización de la vista.
        // Por ejemplo, cargar datos, configurar eventos, etc.
    }

    // Puedes agregar métodos para manejar eventos, acciones, etc.
    // Por ejemplo, para manejar la búsqueda cuando el usuario presiona Enter en el campo de búsqueda.

    // Otros métodos relacionados con la interacción del usuario.

    @FXML
    private void loadHomeView() throws IOException {
        // Carga y muestra la vista 'Home' en el área de contenido principal.
        // Puedes cargar la vista utilizando FXMLLoader y establecerla en mainContentArea.
        App.setRoot("home");
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

    @FXML
    private void handleSearchAction() {
        String searchTerm = searchTextField.getText();
        // Realiza la búsqueda y muestra los resultados.

        //cuando buscas
        List<Object> listatotal = new ArrayList<>(); //lo que devuelve el buscado
        for(Object item: listatotal){


            if(item instanceof Disco){
                Disco i = (Disco)item;
                Image img = new Image(new ByteArrayInputStream(i.getFoto()));
                ImageView imageView = new ImageView(img);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        //abrir disco this.openDisco(i.getId());
                        event.consume();
                    }
                });
                fila.getChildren().add(imageView);

            }
            if(item instanceof Artista){
                Artista i = (Artista)item;
                Image img = new Image(new ByteArrayInputStream(i.getFoto()));
                ImageView imageView = new ImageView(img);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        //abrir disco this.openArtista(i.getId());
                        event.consume();
                    }
                });
                fila.getChildren().add(imageView);
            }
        }

    }



}
