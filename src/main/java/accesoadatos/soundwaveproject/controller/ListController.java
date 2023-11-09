package  accesoadatos.soundwaveproject.controller;

import accesoadatos.soundwaveproject.model.DAO.ListaDAO;
import accesoadatos.soundwaveproject.model.Lista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.List;

public class ListController {
    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnBorrar;

    @FXML
    private ListView<Lista> userListView;


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
        listaDAO.insertLista((Lista) userListView.getItems());
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