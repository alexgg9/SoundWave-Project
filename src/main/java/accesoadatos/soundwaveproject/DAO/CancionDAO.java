package accesoadatos.soundwaveproject.DAO;

import accesoadatos.soundwaveproject.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Disco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CancionDAO {

    private final static String INSERT = "INSERT INTO cancion (id, nombre, duracion, genero, url, id_disco) VALUES (?,?,?,?,?,?)";

    private final static String UPDATE = "UPDATE cancion SET nombre = ?, duracion = ?, genero = ?, url = ?, id_disco = ? WHERE id = ?";

    private final static String DELETE = "DELETE FROM cancion WHERE id = ?";

    private final static String SearchById = "SELECT id, nombre, duracion, genero, id_disco FROM cancion WHERE id = ?";

    private final static String SearchAll = "SELECT id, nombre, duracion, genero, id_disco FROM cancion LIMIT 15";

    private Connection connection;

    public CancionDAO() {
        this.connection= ConnectionMySQL.getConnect();
    }

    public Cancion saveCancion(Cancion cancion) throws SQLException {
        if (cancion == null) {
            return null;
        }

        Cancion existingCancion = getCancionById(cancion.getId());

        try {
            PreparedStatement pst;
            if (existingCancion == null) {
                pst = connection.prepareStatement(INSERT);
                pst.setInt(1, cancion.getId());
                pst.setString(2, cancion.getNombre());
                pst.setInt(3, cancion.getDuracion());
                pst.setString(4, cancion.getGenero());
                pst.setString(5, cancion.getUrl());
                pst.setInt(6, cancion.getDisco().getId());
            } else {
                pst = connection.prepareStatement(UPDATE);
                pst.setString(1, cancion.getNombre());
                pst.setInt(2, cancion.getDuracion());
                pst.setString(3, cancion.getGenero());
                pst.setString(4, cancion.getUrl());
                pst.setInt(5, cancion.getDisco().getId());
                pst.setInt(6, cancion.getId());
            }

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cancion;
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
                    DiscoDAO discoDAO = new DiscoDAO();
                    Disco d1 = discoDAO.getDiscoById((resultSet.getInt("id_disco")));
                    cancion.setDisco(d1);
                    return cancion;
                }
            }
        }
        return null;
    }
}
