package accesoadatos.soundwaveproject.DAO;

import accesoadatos.soundwaveproject.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Disco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscoDAO {

    private final static String INSERT = "INSERT INTO disco (id, nombre, fecha_publicacion, foto, reproducciones, dni_artista) VALUES (?, ?, ?, ?, ?, ?)";

    private final static String UPDATE = "UPDATE disco SET id = ?, nombre = ?, fecha_publicacion = ?, foto = ?, reproducciones = ?, dni_artista=? WHERE id = ?";

    private final static String DELETE =  "DELETE FROM disco WHERE id = ?";

    private final static String SEARCHBYID = "SELECT * FROM disco WHERE id = ?";

    private final static String GETALL = "SELECT * FROM disco LIMIT 15";

    private final static String GETALLCANCIONES = "SELECT id, nombre, duracion, genero, url FROM cancion WHERE id_disco = ?";

    private Connection connection;

    public DiscoDAO() {
        this.connection= ConnectionMySQL.getConnect();
    }

    public Disco insertDisco(Disco disco) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setInt(1, disco.getId());
            ps.setString(2, disco.getNombre());
            ps.setDate(3, Date.valueOf(disco.getFechaPublicacion()));
            ps.setBytes(4, disco.getFoto());
            ps.setString(5, disco.getReproducion());
            ps.setString(6, disco.getArtista().getDni());
            ps.executeUpdate();
        }
        return disco;
    }

    public void updateDisco(Disco disco) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, disco.getNombre());
            ps.setDate(2, Date.valueOf(disco.getFechaPublicacion()));
            ps.setBytes(3, disco.getFoto());
            ps.setString(4, disco.getReproducion());
            ps.setString(5, disco.getArtista().getDni());
            ps.setInt(6, disco.getId());
            ps.executeUpdate();
        }
    }

    public void deleteDisco(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Disco getDiscoByInt(int id) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCHBYID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Disco disco = new Disco();
                    disco.setId(resultSet.getInt("id"));
                    disco.setNombre(resultSet.getString("nombre"));
                    disco.setFechaPublicacion(resultSet.getDate("fecha_publicacion").toLocalDate());
                    disco.setFoto(resultSet.getBytes("foto"));
                    disco.setReproducion(resultSet.getString("reproduccion"));
                    ArtistaDAO artistaDao = new ArtistaDAO();
                    Artista a1 = artistaDao.findByDni(resultSet.getString("dni_artista"));
                    disco.setArtista(a1);
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

