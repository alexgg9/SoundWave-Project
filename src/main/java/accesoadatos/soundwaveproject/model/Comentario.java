package accesoadatos.soundwaveproject.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Comentario {
    private int id;
    private String contenido;
    private LocalDate fecha;
    private Usuario usuario;
    private Lista lista;

    public Comentario() {
        this.id=0;
        this.contenido="";
        this.fecha=null;
        this.usuario=null;
        this.lista=null;

    }

    public Comentario(int id, String contenido, LocalDate fecha, Usuario usuario, Lista lista) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.lista = lista;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
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
        return "Comentario - " +
                "id:" + id +
                ", contenido:'" + contenido + '\'' +
                ", fecha:" + fecha +
                ", usuario:" + usuario.getDni() +
                ", lista:" + lista.getNombre();
    }
}
