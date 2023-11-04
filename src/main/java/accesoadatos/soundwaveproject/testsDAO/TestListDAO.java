package accesoadatos.soundwaveproject.testsDAO;

import accesoadatos.soundwaveproject.model.DAO.ListaDAO;
import accesoadatos.soundwaveproject.model.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Usuario;

import java.sql.SQLException;

public class TestListDAO {
    public static void main(String[] args) throws SQLException {
        ConnectionMySQL connection = new ConnectionMySQL();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ListaDAO listaDAO = new ListaDAO();
        Lista lista = new Lista();
        lista.setNombre("Bangers Xelita");
        lista.setDescripcion("Temazos");
        lista.setSuscripciones(1000);
        Usuario creador = usuarioDAO.getByDni("12345678A");
        lista.setCreador(creador);
        //listaDAO.insertLista(lista);
        int listaId = lista.getId();
        listaDAO.deleteLista(listaId);



    }

}
