package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO extends Lista {
    private final static String FINDALL = "SELECT * FROM lista LIMIT 15";
    private final static String FINDBYID = "SELECT * FROM lista WHERE id = ?";
    private final static String INSERT = "INSERT INTO lista (nombre, descripcion, dni_usuario, suscripciones) VALUES (?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM lista WHERE id = ?";
    private final static String UPDATE = "UPDATE lista SET nombre = ?, descripcion = ?, dni_usuario = ?, suscripciones = ?";
    private static final String SELECT_BY_USUARIO = "SELECT * FROM lista WHERE dni_usuario = ?";


    private static Connection connection;

    public ListaDAO(Connection connection){
        this.connection = connection;
    }

    public ListaDAO() {
        connection = ConnectionMySQL.getConnect();
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
                lista.setSuscripciones(rs.getInt("suscripciones"));
                listas.add(lista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listas;
    }

    public static List<Lista> getListasByUsuario(String usuarioDni) {
        List<Lista> listas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USUARIO)) {
            ps.setString(1, usuarioDni);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Lista lista = new Lista();
                    lista.setNombre(rs.getString("nombre"));
                    lista.setDescripcion(rs.getString("descripcion"));
                    lista.setSuscripciones(rs.getInt("suscripciones"));
                    listas.add(lista);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                    Usuario usuario = UsuarioDAO.getByDni(dniUsuario);
                    suscripciones.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suscripciones;
    }


    public static Lista findById(int id) {
        Lista lista = null;

        try (PreparedStatement statement = connection.prepareStatement(FINDBYID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lista = new Lista();
                    lista.setId(resultSet.getInt("id"));
                    lista.setNombre(resultSet.getString("nombre"));
                    lista.setDescripcion(resultSet.getString("descripcion"));
                    lista.setNombre(resultSet.getString("suscripciones"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertLista(Lista lista) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, lista.getNombre());
            statement.setString(2, lista.getDescripcion());
            statement.setString(3, lista.getCreador().getDni());
            statement.setInt(4, lista.getSuscripciones());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteLista(int id) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    public void updateLista(Lista lista) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, lista.getNombre());
            statement.setString(2, lista.getDescripcion());
            statement.setString(3, lista.getCreador().getDni());
            statement.setInt(4, lista.getSuscripciones());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
