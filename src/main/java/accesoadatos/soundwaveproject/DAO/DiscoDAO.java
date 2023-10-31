package accesoadatos.soundwaveproject.DAO;

import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Disco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscoDAO {

    private final static String INSERT = "INSERT INTO disco (nombre, fechapublicacion, foto, reproducciones) VALUES (?, ?, ?, ?)";

    private final static String UPDATE = "UPDATE disco SET nombre = ?, fechapublicacion = ?, foto = ?, reproducciones = ? WHERE id = ?";;

    private final static String DELETE =  "DELETE FROM disco WHERE id = ?";

    private final static String SEARCHBYID = "SELECT * FROM disco WHERE id = ?";

    private final static String GETALL = "SELECT * FROM disco LIMIT 15";

    private final static String GETALLCANCIONES = "SELECT id, nombre, duracion, genero, url FROM cancion WHERE id_disco = ?";

    private Connection connection;

    public DiscoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertDisco(Disco disco) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setInt(1, disco.getId());
            ps.setString(2, disco.getNombre());
            ps.setDate(3, Date.valueOf(disco.getFechaPublicacion()));
            ps.setBytes(4, disco.getFoto());
            ps.setString(5, disco.getReproducion());
            ps.executeUpdate();
        }
    }

    public void updateDisco(Disco disco) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, disco.getNombre());
            ps.setDate(2, Date.valueOf(disco.getFechaPublicacion()));
            ps.setBytes(3, disco.getFoto());
            ps.setString(4, disco.getReproducion());
            ps.setInt(5, disco.getId());
            ps.executeUpdate();
        }
    }

    public void deleteDisco(String dni) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }

    public Disco getDiscoByDni(String dni) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCHBYID)) {
            preparedStatement.setString(1, dni);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Disco disco = new Disco();
                    disco.setId(resultSet.getInt("dni"));
                    disco.setNombre(resultSet.getString("nombre"));
                    disco.setFechaPublicacion(resultSet.getDate("fecha_publicacion").toLocalDate());
                    disco.setFoto(resultSet.getBytes("foto"));
                    disco.setReproducion(resultSet.getString("reproduccion"));
                    return disco;
                }
            }
        }
        return null;
    }
    public List<Cancion> getCancionesByDiscoId(int discoId) throws SQLException {
        List<Cancion> canciones = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GETALLCANCIONES)) {
            preparedStatement.setInt(1, discoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cancion cancion = new Cancion();
                    cancion.setId(resultSet.getInt("id"));
                    cancion.setNombre(resultSet.getString("nombre"));
                    cancion.setDuracion(resultSet.getInt("duracion"));
                    cancion.setGenero(resultSet.getString("genero"));
                    cancion.setUrl(resultSet.getString("url"));
                    canciones.add(cancion);
                }
            }
        }

        return canciones;
    }


}

