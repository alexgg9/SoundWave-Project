package accesoadatos.soundwaveproject.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Disco {
    private int id;
    private String nombre;
    private LocalDate fechaPublicacion;
    private byte [] foto;
    private String reproduccion;
    private Artista artista;
    private  List<Cancion> canciones;

    public Disco(List<Cancion> canciones) {
        canciones = canciones;
    }

    public Disco() {

    }

    public Disco(int id, String nombre, LocalDate fechaPublicacion, byte[] foto, String reproducion, Artista artista, List<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.fechaPublicacion = fechaPublicacion;
        this.foto = foto;
        this.reproduccion = reproducion;
        this.artista = artista;
        this.canciones = canciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getReproduccion() {
        return reproduccion;
    }

    public void setReproduccion(String reproducion) {
        this.reproduccion = reproducion;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disco disco = (Disco) o;
        return id == disco.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Disco{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", foto=" + Arrays.toString(foto) +
                ", reproducion='" + reproduccion + '\'' +
                ", artista=" + artista +
                ", Canciones=" + canciones +
                '}';
    }
}
