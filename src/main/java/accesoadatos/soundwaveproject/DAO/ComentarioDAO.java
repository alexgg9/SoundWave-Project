package accesoadatos.soundwaveproject.DAO;

import accesoadatos.soundwaveproject.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Comentario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {

    private final static String INSERT = "INSERT INTO comentario (id, contenido, fecha, dni_usuario, id_lista) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE comentario SET contenido = ?, fecha=?, dni_usuario=?, id_lista=? WHERE id=?";
    private final static String DELETE = "DELETE FROM comentario WHERE id = ?";
    private final static String SEARCHBYID = "SELECT id, contenido, fecha, dni_usuario,id_lista FROM comentario WHERE id = ?";
    private final static String SEARCHALL = "SELECT * FROM comentario LIMIT 15";
    private final static String SEARCHBYUSER = "SELECT id, contenido, fecha, dni_usuario,id_lista FROM comentario WHERE dni_usuario = ?";
    private final static String SEARCHBYIDLIST = "SELECT id, contenido, fecha, dni_usuario,id_lista FROM comentario WHERE id_lista = ?";


    private Connection conn;

    public ComentarioDAO(Connection conn){
        this.conn = conn;
    }

    public ComentarioDAO(){
        this.conn= ConnectionMySQL.getConnect();
    }

    public Comentario save(Comentario entity) throws SQLException {
        Comentario result = null;

        if (entity != null) {
            Comentario existingComment = findById(entity.getId());

            try (PreparedStatement pst = conn.prepareStatement(INSERT)) {
                if (existingComment == null) {
                    pst.setInt(1, entity.getId());
                    pst.setString(2, entity.getContenido());
                    pst.setDate(3, new java.sql.Date(entity.getFecha().getTime()));
                    pst.setString(4, entity.getUsuario().getDni());
                    pst.setInt(5, entity.getLista().getId());
                    pst.executeUpdate();
                } else {
                    pst.setString(1, entity.getContenido());
                    pst.setDate(2, new java.sql.Date(entity.getFecha().getTime()));
                    pst.setString(3, entity.getUsuario().getDni());
                    pst.setInt(4, entity.getLista().getId());
                    pst.setInt(5, entity.getId());
                    pst.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public List<Comentario> findAll() throws SQLException {
        List<Comentario> result = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement(SEARCHALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setId(res.getInt("id"));
                    comentario.setContenido(res.getString("contenido"));
                    comentario.setFecha(res.getDate("fecha"));
                    comentario.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    comentario.setLista(findListById(res.getInt("id_lista")));

                    result.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones en caso de error
        }
        return result;
    }



    public Comentario findById(int id) throws SQLException {
        Comentario result = null;
        try (PreparedStatement pst = this.conn.prepareStatement(SEARCHBYID)) {
            pst.setInt(1, id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result = new Comentario();
                    result.setId(res.getInt("id"));
                    result.setContenido(res.getString("contenido"));
                    result.setFecha(res.getDate("fecha"));
                    result.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    result.setLista(findListById(res.getInt("id_lista")));

                }
            }
        }
        return result;
    }

    public List<Comentario> findAllByUser(String dniUsuario) throws SQLException {
        List<Comentario> comentarios = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(SEARCHBYUSER)) {
            pst.setString(1, dniUsuario);
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setId(res.getInt("id"));
                    comentario.setContenido(res.getString("contenido"));
                    comentario.setFecha(res.getDate("fecha"));
                    comentario.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    comentario.setLista(findListById(res.getInt("id_lista")));
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    public List<Comentario> findAllByList(int listaId) throws SQLException {
        List<Comentario> comentarios = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(SEARCHBYIDLIST)) {
            pst.setInt(1, listaId);
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setId(res.getInt("id"));
                    comentario.setContenido(res.getString("contenido"));
                    comentario.setFecha(res.getDate("fecha"));
                    comentario.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    comentario.setLista(findListById(res.getInt("id_lista")));
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }



    public void delete(Comentario entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
                pst.setInt(1, entity.getId());
                pst.executeUpdate();
            }
        }
    }




}
