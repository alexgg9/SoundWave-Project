package accesoadatos.soundwaveproject.DAO;

public class ArtistaDAO {
    private final static String INSERT = "INSERT INTO Artista (dni, nombre, nacionalidad, foto) VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE Artista SET nombre = ?, nacionalidad = ?, foto = ? WHERE dni = ?";

    private final static String SEARCHBYDNI = "SELECT dni, nombre, nacionalidad, foto FROM Artista WHERE dni = ?";
    private final static String DELETE = "DELETE FROM Artista WHER dni = ?";


}
