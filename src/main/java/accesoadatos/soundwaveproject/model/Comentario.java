package accesoadatos.soundwaveproject.model;

import java.util.Date;
import java.util.Objects;

public class Comentario {
    private int id;
    private String contenido;
    private Date fecha;
    private Usuario dniU;
    private Lista idL;

    public Comentario() {
        this.id=0;
        this.contenido="";
        this.fecha=null;
        this.dniU=null;
        this.idL=null;

    }

    public Comentario(int id, String contenido, Date fecha, Usuario dniU, Lista idL) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.dniU = dniU;
        this.idL = idL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getDniU() {
        return dniU;
    }

    public void setDniU(Usuario dniU) {
        this.dniU = dniU;
    }

    public Lista getIdL() {
        return idL;
    }

    public void setIdL(Lista idL) {
        this.idL = idL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", fecha=" + fecha +
                ", dniU=" + dniU +
                ", idL=" + idL +
                '}';
    }
}
