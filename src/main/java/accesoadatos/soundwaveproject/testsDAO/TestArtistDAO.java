package accesoadatos.soundwaveproject.testsDAO;

import accesoadatos.soundwaveproject.model.DAO.ArtistaDAO;
import accesoadatos.soundwaveproject.model.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Artista;

import java.sql.SQLException;

public class TestArtistDAO {
    public static void main(String[] args) {

        String filePathString = "resources/img/kendrick.jpg";
        try {
            ConnectionMySQL connection = new ConnectionMySQL();
            ArtistaDAO artistaDAO = new ArtistaDAO();
            Artista artista = new Artista();
            artista.setDni("87654321C");
            artista.setNombre("Kendrick Lamar");
            artista.setNacionalidad("EEUU");
            artista.setFoto(filePathString.getBytes());
            Artista savedArtista = artistaDAO.save(artista);
            //artistaDAO.delete(artista);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}