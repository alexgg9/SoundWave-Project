package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Comentario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {

    private final static String INSERT = "INSERT INTO comentario (contenido, fecha, dni_usuario, id_lista) VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE comentario SET contenido = ?, fecha=?, dni_usuario=?, id_lista=? WHERE id=?";
    private final static String DELETE = "DELETE FROM comentario WHERE id = ?";
    private final static String SEARCHBYID = "SELECT id, contenido, fecha, dni_usuario,id_lista FROM comentario WHERE id = ?";
    private final static String SEARCHALL = "SELECT * FROM comentario LIMIT 15";
    private final static String SEARCHBYUSER = "SELECT id, contenido, fecha, dni_usuario,id_lista FROM comentario WHERE dni_usuario = ?";
    private final static String SEARCHBYIDLIST = "SELECT id, contenido, fecha, dni_usuario,id_lista FROM comentario WHERE id_lista = ?";
    private final static String SEARCH_BY_LISTA_ID = "SELECT id, contenido, fecha, dni_usuario, id_lista FROM comentario WHERE id_lista = ?";


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
                    pst.setString(1, entity.getContenido());
                    pst.setDate(2, Date.valueOf(entity.getFecha()));
                    pst.setString(3, entity.getUsuario().getDni());
                    pst.setInt(4, entity.getLista().getId());
                    pst.executeUpdate();
                } else {
                    pst.setString(1, entity.getContenido());
                    pst.setDate(2, Date.valueOf(entity.getFecha()));
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
                    comentario.setFecha(res.getDate("fecha").toLocalDate());
                    comentario.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    comentario.setLista(findById(res.getInt("id_lista")).getLista());

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
                    result.setFecha(res.getDate("fecha").toLocalDate());
                    result.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));

                    // Obtener la Lista directamente desde la base de datos si es necesario
                    int idLista = res.getInt("id_lista");
                    Lista lista = ListaDAO.findById(idLista);

                    // Establecer la Lista en el Comentario
                    result.setLista(lista);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones en caso de error
        }
        return result;
    }



    public List<Comentario> findCommentsByListaId(int idLista) throws SQLException {
        List<Comentario> result = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement(SEARCH_BY_LISTA_ID)) {
            pst.setInt(1, idLista);  // Agregamos el parámetro id_lista a la consulta
            Lista lista = ListaDAO.findById(idLista);  // Obtener la lista fuera del bucle

            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setId(res.getInt("id"));
                    comentario.setContenido(res.getString("contenido"));
                    comentario.setFecha(res.getDate("fecha").toLocalDate());
                    comentario.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    comentario.setLista(lista);  // Establecer la misma lista para todos los comentarios

                    result.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones en caso de error
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
                    comentario.setFecha(res.getDate("fecha").toLocalDate());
                    comentario.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    comentario.setLista(findById(res.getInt("id_lista")).getLista());
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
                    comentario.setFecha(res.getDate("fecha").toLocalDate());
                    comentario.setUsuario(UsuarioDAO.getByDni(res.getString("dni_usuario")));
                    comentario.setLista(findById(res.getInt("id_lista")).getLista());
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }






    public void delete(int idComentario) {

        try (PreparedStatement pst = this.conn.prepareStatement(DELETE)) {
            pst.setInt(1, idComentario);
            pst.executeUpdate();




        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de error al eliminar el comentario
        }
    }

}
