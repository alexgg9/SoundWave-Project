package accesoadatos.soundwaveproject.testsDAO;

import accesoadatos.soundwaveproject.DAO.ArtistaDAO;
import accesoadatos.soundwaveproject.DAO.DiscoDAO;
import accesoadatos.soundwaveproject.SQLConnection.ConnectionMySQL;
import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Disco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestDiscDAO {
    public static void main(String[] args) {

        String filePathString =  "C:/Users/Alex/Intellj/SoundWaveProject/src/main/resources/img/DiscCovers/ToPimpAButterfly.jpg";
            try {
                ConnectionMySQL connection = new ConnectionMySQL();
                DiscoDAO discoDAO = new DiscoDAO();
                Disco disco = new Disco();
                disco.setNombre("ToPimpAButterfly");
                disco.setFechaPublicacion(LocalDate.of(2022, 10, 15));
                byte[] foto = Files.readAllBytes(Paths.get(filePathString));
                disco.setFoto(foto);
                disco.setReproduccion("100000");
                ArtistaDAO artistaDAO = new ArtistaDAO();
                String dniArtista = "87654321C";
                Artista artistaBuscado = new Artista();
                artistaBuscado.setDni(dniArtista);
                Artista artistaEncontrado = artistaDAO.findByDni(artistaBuscado.getDni());
                disco.setArtista(artistaDAO.findByDni(artistaEncontrado.getDni()));
                Disco savedDisco = discoDAO.insertDisco(disco);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
    }
}
