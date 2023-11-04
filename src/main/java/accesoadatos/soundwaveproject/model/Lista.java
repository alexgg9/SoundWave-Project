package accesoadatos.soundwaveproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lista {
    private int id;
    private String nombre;
    private String descripcion;
    private int suscripciones;
    private Usuario creador;
    private List<Comentario> comentarios;


    public Lista() {
    }

    public Lista(int id,String nombre, String descripcion, int suscripciones, Usuario creador) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.suscripciones = suscripciones;
        this.creador = creador;
    }

    public Lista(int id, String nombre, String descripcion, int suscripciones, Usuario creador, List<Comentario> comentarios, List<Usuario> seguidores) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.suscripciones = suscripciones;
        this.creador = creador;
        this.comentarios = comentarios;

    }

    public Lista(int id, String nombre, String descripcion, ArrayList<Usuario> usuarios, ArrayList<Comentario> comentarios, ArrayList<Usuario> usuarios1) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(int suscripciones) {
        this.suscripciones = suscripciones;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lista lista = (Lista) o;
        return id == lista.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", suscripciones=" + suscripciones +
                ", creador=" + creador +
                ", comentarios=" + comentarios +
                '}';
    }

}
