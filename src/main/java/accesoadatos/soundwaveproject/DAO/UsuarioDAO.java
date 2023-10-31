package accesoadatos.soundwaveproject.DAO;

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
    public UsuarioDAO(String dni) {
        getByDni(dni);
    }
    public UsuarioDAO(String dni, String nombre, String correo, String contraseña, byte[] foto, List<Lista> misListas, List<Comentario> comentarios, List<Lista> suscripciones) {
        super(dni, nombre, correo, contraseña, foto, misListas, comentarios, suscripciones);
    }

    public void save(Usuario usuario) throws SQLException {
        // Verifica si el usuario ya existe en la base de datos
        if (usuarioExists(usuario.getDni())) {
            // Si el usuario existe, llama al método update en lugar de insertar
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
    // Método para verificar si un usuario ya existe en la base de datos
    private boolean usuarioExists(String dni) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SELECTBYID)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Devuelve true si el usuario existe, de lo contrario, false.
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

            // Si rowsAffected es mayor que 0, significa que se ha actualizado al menos una fila.
            return rowsAffected > 0;
        }
    }

    public boolean remove() {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setString(1, this.getDni());
            int rowsAffected = ps.executeUpdate();

            // Si rowsAffected es mayor que 0, significa que se eliminó al menos una fila.
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getByDni(String dni) {
        try (PreparedStatement ps = connection.prepareStatement(SELECTBYID)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    this.setDni(rs.getString("dni"));
                    this.setNombre(rs.getString("nombre"));
                    this.setCorreo(rs.getString("correo"));
                    this.setContraseña(rs.getString("contraseña"));
                    this.setFoto(rs.getBytes("foto"));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                        new ArrayList<Lista>(), // Puedes inicializar estas listas según tus necesidades
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
