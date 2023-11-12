package accesoadatos.soundwaveproject.testsDAO;

import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.DAO.ComentarioDAO;
import accesoadatos.soundwaveproject.model.DAO.ListaDAO;
import accesoadatos.soundwaveproject.model.DAO.UsuarioDAO;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestCommentDAO {
    public static void main(String[] args) throws SQLException, IOException {
        ConnectionMySQL connection = new ConnectionMySQL();
        ComentarioDAO comentarioDAO = new ComentarioDAO();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String filePathString = "C:/Users/Alex/Intellj/SoundWaveProject/src/main/resources/img/userPics/foto1.png";
        Path imagePath = Paths.get(filePathString);
        byte[] fotoBytes = Files.readAllBytes(imagePath);
        Usuario usuario = new Usuario("12345678c", "PruebaUsuario", "correo@example.com", "contraseña1", fotoBytes);
        //usuarioDAO.save(usuario);
        ListaDAO listaDAO = new ListaDAO();
        Lista lista = new Lista(1,"Pedazo PlayList","Temazos",160,usuario);
        //listaDAO.insertLista(lista);
        Comentario comentario = new Comentario(3, "Muy guapa la lista", LocalDate.of(2022, 10, 15), usuario, lista);
        //comentarioDAO.save(comentario);

    }
}
