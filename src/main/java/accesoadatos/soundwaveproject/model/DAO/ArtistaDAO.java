package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Disco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO {
    private final static String INSERT = "INSERT INTO Artista (dni, nombre, nacionalidad, foto) VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE Artista SET nombre = ?, nacionalidad = ?, foto = ? WHERE dni = ?";
    private final static String SEARCHBYDNI = "SELECT dni, nombre, nacionalidad, foto FROM Artista WHERE dni = ?";
    private final static String DELETE = "DELETE FROM Artista WHERE dni = ?";
    private final static String SEARCHDISC = "SELECT id, nombre, fecha_publicacion, foto FROM Disco WHERE dni_artista = ?";


    private Connection conn;

    public ArtistaDAO(Connection conn){
        this.conn = conn;
    }

    public ArtistaDAO(){
        this.conn= ConnectionMySQL.getConnect();
    }

    public Artista findByDni(String dni) throws SQLException {
        Artista result = null;
        try (PreparedStatement pst = this.conn.prepareStatement(SEARCHBYDNI)) {
            pst.setString(1, dni);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result = new Artista();
                    result.setDni(res.getString("dni"));
                    result.setNombre(res.getString("nombre"));
                    result.setNacionalidad(res.getString("nacionalidad"));
                    result.setFoto(res.getBytes("foto"));

                }
            }
        }
        return result;
    }

    public List<Disco> getDiscosByArtista(Artista artista) throws SQLException {
        List<Disco> discos = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement(SEARCHDISC)) {
            pst.setString(1, artista.getDni());
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Disco disco = new Disco();
                    disco.setId(res.getInt("id"));
                    disco.setNombre(res.getString("nombre"));
                    disco.setFechaPublicacion(res.getDate("fecha_publicacion").toLocalDate());
                    disco.setFoto(res.getBytes("foto"));
                    discos.add(disco);
                }
            }
        }
        return discos;
    }

    public Artista save(Artista entity) throws SQLException {
        Artista result = null;
        if (entity != null) {
            Artista existingArtista = findByDni(entity.getDni());
            try {
                if (existingArtista == null) {
                    try (PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                        pst.setString(1, entity.getDni());
                        pst.setString(2, entity.getNombre());
                        pst.setString(3, entity.getNacionalidad());
                        pst.setBytes(4, entity.getFoto());
                        pst.executeUpdate();
                    }
                } else {
                    try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
                        pst.setString(1, entity.getNombre());
                        pst.setString(2, entity.getNacionalidad());
                        pst.setBytes(3, entity.getFoto());
                        pst.setString(4, entity.getDni());
                        pst.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result = entity;
        }
        return result;
    }

    public void delete(Artista entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
                pst.setString(1, entity.getDni());
                pst.executeUpdate();
            }
        }
    }



}
