package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Disco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO {

    private final static String INSERT = "INSERT INTO cancion (nombre, duracion, genero, url, id_disco) VALUES (?,?,?,?,?)";

    private final static String UPDATE = "UPDATE cancion SET nombre = ?, duracion = ?, genero = ?, url = ?, id_disco = ? WHERE id = ?";

    private final static String DELETE = "DELETE FROM cancion WHERE id = ?";

    private final static String SearchById = "SELECT id, nombre, duracion, genero, id_disco FROM cancion WHERE id = ?";

    private final static String SEARCHALL = "SELECT id, nombre, duracion, genero,url, id_disco FROM cancion LIMIT 15";

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
                pst.setString(1, cancion.getNombre());
                pst.setInt(2, cancion.getDuracion());
                pst.setString(3, cancion.getGenero());
                pst.setString(4, cancion.getUrl());
                pst.setInt(5, cancion.getDisco().getId());
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

    public List<Cancion> getAllCanciones() {
        List<Cancion> canciones = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SEARCHALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cancion cancion = new Cancion();
                cancion.setId(rs.getInt("id"));
                cancion.setNombre(rs.getString("nombre"));
                cancion.setDuracion(rs.getInt("duracion"));
                cancion.setGenero(rs.getString("genero"));
                cancion.setUrl(rs.getString("url"));
                DiscoDAO discoDAO = new DiscoDAO();
                Disco d1 = discoDAO.getDiscoById((rs.getInt("id_disco")));
                cancion.setDisco(d1);
                canciones.add(cancion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canciones;
    }
}

