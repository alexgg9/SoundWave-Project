package  accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.DAO.ListaDAO;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.model.singleton.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListController {
    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnBorrar;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField suscripcionesField;
    @FXML
    private ListView<Lista> userListView;


    @FXML
    public void back() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void borrar(ActionEvent event) throws SQLException {
        Lista lista = userListView.getSelectionModel().getSelectedItem();
        if (lista!= null) {
            userListView.getItems().remove(lista);
            ListaDAO listaDAO = new ListaDAO();
            listaDAO.deleteLista(lista.getId());
        }
    }

    @FXML
    void save(ActionEvent event) {
        ListaDAO listaDAO = new ListaDAO();
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        int suscripcion = Integer.parseInt(suscripcionesField.getText());
        Usuario usuario = UserSession.getInstance().getUsuarioActual();
        Lista lista = new Lista(nombre,descripcion,suscripcion,usuario);
        listaDAO.insertLista(lista);
    }

    public void initialize() {
        List<Lista> listas = getListaFromDataSource();
        ObservableList<Lista> observableListas = FXCollections.observableArrayList(listas);
        userListView.setItems(observableListas);
    }

    private List<Lista> getListaFromDataSource() {
        ListaDAO listaDAO = new ListaDAO();
        return listaDAO.findAll();
    }
}