package accesoadatos.soundwaveproject.testsDAO;

import accesoadatos.soundwaveproject.model.DAO.CancionDAO;
import accesoadatos.soundwaveproject.model.DAO.DiscoDAO;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Disco;

import java.sql.SQLException;

public class TestSongDAO {

    public static void main(String[] args) {
        DiscoDAO discoDao = new DiscoDAO();
        CancionDAO cancionDAO = new CancionDAO();
        int discoId = 2;
        Disco disco;
            try {
                disco = discoDao.getDiscoById(discoId);
                Cancion cancion = new Cancion();
                cancion.setId(1);
                cancion.setNombre("King Kunta");
                cancion.setDuracion(234);
                cancion.setGenero("HipHop");
                cancion.setUrl("/resources/songs/Kendrick Lamar - King Kunta.mp3");
                cancion.setDisco(disco);
                cancionDAO.saveCancion(cancion);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

