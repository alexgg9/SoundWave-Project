package accesoadatos.soundwaveproject.DAO;

import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO extends Lista {
    private final static String FINDALL = "SELECT * FROM lista";
    private final static String FINDBYID = "SELECT * FROM lista WHERE id = ?";
    private final static String INSERT = "INSERT INTO lista (nombre, descripcion, dni_usuario, suscripciones) VALUES (?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM lista WHERE id = ?";
    private final static String UPDATE = "UPDATE lista SET nombre = ?, descripcion = ?, dni_usuario = ?, suscripciones = ?";

    private static Connection connection;

    public ListaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Lista> findAll() {
        List<Lista> listas = new ArrayList<>();

        try (PreparedStatement pst = connection.prepareStatement(FINDALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Lista lista = new Lista();
                lista.setId(rs.getInt("id"));
                lista.setNombre(rs.getString("nombre"));
                lista.setDescripcion(rs.getString("descripcion"));
                lista.setCreador(UsuarioDAO.getByDni(rs.getString("dni_usuario")));
                lista.setSuscripciones(getSuscripcionesByListaId(lista.getId()));
                listas.add(lista);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja las excepciones apropiadamente.
        }

        return listas;
    }

    private List<Usuario> getSuscripcionesByListaId(int listaId) {
        List<Usuario> suscripciones = new ArrayList<>();

        try (PreparedStatement pst = connection.prepareStatement("SELECT dni_usuario FROM lista WHERE id = ?")) {
            pst.setInt(1, listaId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String dniUsuario = rs.getString("dni_usuario");
                    // Aquí puedes cargar el objeto Usuario usando UsuarioDAO o tu lógica de acceso a datos
                    Usuario usuario = UsuarioDAO.getByDni(dniUsuario);
                    suscripciones.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja las excepciones apropiadamente.
        }

        return suscripciones;
    }


    public Lista findById(int id) {
        Lista lista = null;

        try (PreparedStatement statement = connection.prepareStatement(FINDBYID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lista = new Lista();
                    lista.setId(resultSet.getInt("id"));
                    lista.setNombre(resultSet.getString("nombre"));
                    lista.setDescripcion(resultSet.getString("descripcion"));
                }
            }
        } catch (SQLException e) {
            // Maneja las excepciones apropiadamente.
        }

        return lista;
    }

    public boolean insertLista(Lista lista) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, lista.getNombre());
            statement.setString(2, lista.getDescripcion());
            statement.setString(3, lista.getCreador().getDni());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteLista(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Maneja las excepciones apropiadamente.
        }
    }

    public void updateLista(Lista lista) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, lista.getNombre());
            statement.setString(2, lista.getDescripcion());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Maneja las excepciones apropiadamente.
        }
    }
}
