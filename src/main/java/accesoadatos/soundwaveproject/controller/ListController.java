package  accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.App;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.DAO.CancionDAO;
import accesoadatos.soundwaveproject.model.DAO.ComentarioDAO;
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
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListController {
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnAñadirComentario;
    @FXML
    private TextField comentarioField;
    @FXML
    private Button btnBorrarComentario;

    @FXML
    private Button btnBorrar;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private ListView<Lista> userListView;
    @FXML
    private ListView<Cancion> songs;
    @FXML
    private ListView<Comentario> coments;


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
        userListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Lista selectedLista = userListView.getSelectionModel().getSelectedItem();
                if (selectedLista != null) {
                    int idLista = selectedLista.getId();
                    List<Comentario> comentarios = getComentarioFromDataSource(idLista);
                    coments.setItems(FXCollections.observableArrayList(comentarios));
                }
            }
        });

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
    @FXML
    void eliminarComentarioSeleccionado(ActionEvent event) {
        Comentario comentarioSeleccionado = coments.getSelectionModel().getSelectedItem();

        if (comentarioSeleccionado != null) {
            int idComentario = comentarioSeleccionado.getId();
            ComentarioDAO comentarioDAO = new ComentarioDAO();

            comentarioDAO.delete(idComentario);

            coments.getItems().remove(comentarioSeleccionado);

            Utils.showPopUp("Borrar Comentario", "", "Comentario eliminado correctamente", Alert.AlertType.INFORMATION);
        } else {
            Utils.showPopUp("Error", "Error al borrar comentario", "Selecciona un comentario para borrar", Alert.AlertType.WARNING);
        }
    }


    private List<Comentario> getComentarioFromDataSource(int idLista) {
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        try {
            return comentarioDAO.findCommentsByListaId(idLista);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @FXML
    public void agregarComentarioALista(ActionEvent event) {
        Lista listaSeleccionada = userListView.getSelectionModel().getSelectedItem();
        String nuevoComentarioTexto = comentarioField.getText();
        if (listaSeleccionada != null && nuevoComentarioTexto != null && !nuevoComentarioTexto.isEmpty()) {
            Usuario usuarioActual = UserSession.getInstance().getUsuarioActual();
            Comentario nuevoComentario = new Comentario();
            nuevoComentario.setContenido(nuevoComentarioTexto);
            nuevoComentario.setUsuario(usuarioActual);
            int idNuevoComentario = ListaDAO.insertarComentarioEnLista(listaSeleccionada.getId(), nuevoComentario);
            List<Comentario> comentariosActualizados = getComentarioFromDataSource(idNuevoComentario);
            ObservableList<Comentario> observableComentarios = FXCollections.observableArrayList(comentariosActualizados);
            coments.setItems(observableComentarios);
            Utils.showPopUp("Agregar Comentario", "", "Comentario añadido a la lista correctamente", Alert.AlertType.INFORMATION);
        } else {
            Utils.showPopUp("Error", "Error al agregar comentario", "Selecciona una lista y asegúrate de ingresar el texto del comentario", Alert.AlertType.WARNING);
        }
    }





}