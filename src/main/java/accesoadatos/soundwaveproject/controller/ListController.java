package  accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.DAO.CancionDAO;
import accesoadatos.soundwaveproject.model.DAO.ListaDAO;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.model.singleton.UserSession;
import accesoadatos.soundwaveproject.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private ListView<Cancion> songs;


    @FXML
    public void back() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void borrar(ActionEvent event) throws SQLException {
        Lista lista = userListView.getSelectionModel().getSelectedItem();
        if (lista != null) {
            userListView.getItems().remove(lista);
            ListaDAO listaDAO = new ListaDAO();
            listaDAO.deleteLista(lista.getId());
            Utils.showPopUp("Borrar Lista", "", "Lista eliminada correctamente", Alert.AlertType.INFORMATION);
            actualizarListas();
        } else {
            Utils.showPopUp("Error", "Error al borrar lista", "Selecciona una lista para borrar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void agregarCancionALista(ActionEvent event) {
        Lista listaSeleccionada = userListView.getSelectionModel().getSelectedItem();
        Cancion cancionSeleccionada = songs.getSelectionModel().getSelectedItem();

        if (listaSeleccionada != null && cancionSeleccionada != null) {
            ListaDAO.agregarCancionALista(listaSeleccionada.getId(), cancionSeleccionada.getId());
            Utils.showPopUp("Agregar Canción", "", "Canción añadida a la lista correctamente", Alert.AlertType.INFORMATION);
        } else {
            Utils.showPopUp("Error", "Error al agregar canción", "Selecciona una lista y una canción", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void suscribirseALista(ActionEvent event) {
        Lista listaSeleccionada = userListView.getSelectionModel().getSelectedItem();

        if (listaSeleccionada != null) {
            Usuario usuarioActual = UserSession.getInstance().getUsuarioActual();
            String dniUsuarioActual = usuarioActual.getDni();

            ListaDAO listaDAO = new ListaDAO();
            boolean suscrito = ListaDAO.suscribirse(dniUsuarioActual, listaSeleccionada.getId());
            if (suscrito) {
                Utils.showPopUp("Suscripción", "", "Te has suscrito correctamente", Alert.AlertType.INFORMATION);
                actualizarListas();
            } else {
                Utils.showPopUp("Error", "Error de suscripción", "Ya estás suscrito a esta lista", Alert.AlertType.WARNING);
            }
        } else {
            Utils.showPopUp("Error", "Error de suscripción", "Selecciona una lista para suscribirte", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void save(ActionEvent event) {
        ListaDAO listaDAO = new ListaDAO();
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        Usuario usuario = UserSession.getInstance().getUsuarioActual();
        Lista lista = new Lista(nombre, descripcion, 0, usuario);
        listaDAO.insertLista(lista);
        Utils.showPopUp("Guardar Lista", "", "Lista guardada correctamente", Alert.AlertType.INFORMATION);
        actualizarListas();
    }

    public void initialize() {
        actualizarListas();
        List<Cancion> canciones = getCancionesFromDataSource();
        ObservableList<Cancion> observableCanciones = FXCollections.observableArrayList(canciones);
        songs.setItems(observableCanciones);
    }

    private void actualizarListas() {
        List<Lista> listas = getListaFromDataSource();
        ObservableList<Lista> observableListas = FXCollections.observableArrayList(listas);
        userListView.setItems(observableListas);


        Lista listaSeleccionada = userListView.getSelectionModel().getSelectedItem();
        if (listaSeleccionada != null) {
            String dniUsuarioActual = UserSession.getInstance().getUsuarioActual().getDni();
            int idListaSeleccionada = listaSeleccionada.getId();
            int numSuscriptores = ListaDAO.getNumeroSuscriptores(dniUsuarioActual, idListaSeleccionada);
            listaSeleccionada.setSuscripciones(numSuscriptores);
        }
    }


    private List<Lista> getListaFromDataSource() {
        ListaDAO listaDAO = new ListaDAO();
        return listaDAO.findAll();
    }

    private List<Cancion> getCancionesFromDataSource() {
        CancionDAO cancionDAO = new CancionDAO();
        return cancionDAO.getAllCanciones();
    }
}