package accesoadatos.soundwaveproject.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class Disco {
    private String dni;
    private String nombre;
    private LocalDate fechaPublicacion;
    private byte [] foto;
    private String reproducion;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getReproducion() {
        return reproducion;
    }

    public void setReproducion(String reproducion) {
        this.reproducion = reproducion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disco disco = (Disco) o;
        return dni.equals(disco.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Disco{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", foto=" + Arrays.toString(foto) +
                ", reproducion='" + reproducion + '\'' +
                '}';
    }
}
