package accesoadatos.soundwaveproject.DAO;

import accesoadatos.soundwaveproject.model.Cancion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CancionDAO {

    private final static String INSERT ="INSERT INTO cancion (id, nombre, duracion ,genero,url) VALUES (?,?,?,?,?)";

    private final static String UPDATE ="UPDATE cancion SET nombre = ?, duracion = ?, genero = ?, url = ? WHERE id = ?";

    private final static String DELETE = "DELETE FROM cancion WHERE id = ?";

    private final static String SearchById = "SELECT id, nombre, duracion, genero FROM cancion WHERE id = ?";

    private  final static  String SearchAll = "SELECT id, nombre, duracion, genero  FROM cancion LIMIT 15";


    private Connection connection;

    public CancionDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertCancion(Cancion cancion) throws SQLException, SQLException {

        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, cancion.getNombre());
            ps.setInt(2, cancion.getDuracion());
            ps.setString(3, cancion.getGenero());
            ps.setString(4, cancion.getUrl());
            ps.executeUpdate();
        }
    }

    public void updateCancion(Cancion cancion) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, cancion.getNombre());
            ps.setInt(2, cancion.getDuracion());
            ps.setString(3, cancion.getGenero());
            ps.setString(4, cancion.getUrl());
            ps.setInt(5, cancion.getId());
            ps.executeUpdate();
        }
    }

    public void deleteCancion(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    public Cancion getCancionById(int id) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SearchById)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Cancion cancion = new Cancion();
                    cancion.setId(resultSet.getInt("id"));
                    cancion.setNombre(resultSet.getString("nombre"));
                    cancion.setDuracion(resultSet.getInt("duracion"));
                    cancion.setGenero(resultSet.getString("genero"));
                    return cancion;
                }
            }
        }
        return null;
    }
}
