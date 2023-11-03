package accesoadatos.soundwaveproject.DAO;

import accesoadatos.soundwaveproject.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Usuario {
    private final static String INSERT ="INSERT INTO usuario (dni,nombre,correo,contraseña,foto) VALUES(?,?,?,?,?)";
    private final static String UPDATE ="UPDATE usuario SET nombre=?,correo=?,contraseña=?,foto=? WHERE dni=?";
    private final static String DELETE="DELETE FROM usuario WHERE dni=?";
    private final static String SELECTBYID="SELECT dni,nombre,correo,contraseña,foto FROM usuario WHERE dni=?";
    private final static String SELECTALL="SELECT dni,nombre,correo,contraseña,foto FROM usuario";

    private static Connection connection;

    public UsuarioDAO(Connection conn){
        this.connection = conn;
    }

    public UsuarioDAO(){
        this.connection= ConnectionMySQL.getConnect();
    }

    public void save(Usuario usuario) throws SQLException {
        if (usuarioExists(usuario.getDni())) {
            update(usuario);
        } else {
            try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
                ps.setString(1, usuario.getDni());
                ps.setString(2, usuario.getNombre());
                ps.setString(3, usuario.getCorreo());
                ps.setString(4, usuario.getContraseña());
                ps.setBytes(5, usuario.getFoto());
                ps.executeUpdate();
            }
        }
    }

    private boolean usuarioExists(String dni) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SELECTBYID)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    public boolean update(Usuario usuario) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContraseña());
            ps.setBytes(4, usuario.getFoto());
            ps.setString(5, usuario.getDni());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public void delete(Usuario entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = connection.prepareStatement(DELETE)) {
                pst.setString(1, entity.getDni());
                pst.executeUpdate();
            }
        }
    }


    public static Usuario getByDni(String dni) {
        Usuario usuario = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECTBYID)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setDni(rs.getString("dni"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContraseña(rs.getString("contraseña"));
                    usuario.setFoto(rs.getBytes("foto"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECTALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getBytes("foto"),
                        new ArrayList<Lista>(),
                        new ArrayList<Comentario>(),
                        new ArrayList<Lista>()
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }





}
