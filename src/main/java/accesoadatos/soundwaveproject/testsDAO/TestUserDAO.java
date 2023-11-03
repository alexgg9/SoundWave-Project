package accesoadatos.soundwaveproject.testsDAO;

import accesoadatos.soundwaveproject.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;
import accesoadatos.soundwaveproject.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestUserDAO {

    public static void main(String[] args) {
        List<Lista> misListas = new ArrayList<>();
        List<Comentario> comentarios = new ArrayList<>();
        List<Lista> suscripciones = new ArrayList<>();
        String filePathString = "C:/Users/Alex/Intellj/SoundWaveProject/src/main/resources/img/userPics/foto1.png";

        try {
            Path imagePath = Paths.get(filePathString);
            byte[] fotoBytes = Files.readAllBytes(imagePath);
            Usuario usuario = new Usuario("12345678A", "Alex", "alex@example.com", Utils.encryptSHA256("1234"), fotoBytes);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.save(usuario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

