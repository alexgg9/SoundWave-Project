package accesoadatos.soundwaveproject.model;

import java.util.List;
import java.util.Objects;

public class Lista {
    private int id;
    private String nombre;
    private String descripcion;
    private String dni_usuario;
    private int suscripciones;
    Usuario creados;
    List<Comentario> comentarios;
    List<Usuario> seguidores;

    public Lista() {
    }

    public Lista(int id, String nombre, String descripcion, String dni_usuario, int suscripciones, Usuario creados, List<Comentario> comentarios, List<Usuario> seguidores) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dni_usuario = dni_usuario;
        this.suscripciones = suscripciones;
        this.creados = creados;
        this.comentarios = comentarios;
        this.seguidores = seguidores;
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

    public String getDni_usuario() {
        return dni_usuario;
    }

    public void setDni_usuario(String dni_usuario) {
        this.dni_usuario = dni_usuario;
    }

    public int getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(int suscripciones) {
        this.suscripciones = suscripciones;
    }

    public Usuario getCreados() {
        return creados;
    }

    public void setCreados(Usuario creados) {
        this.creados = creados;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
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
                ", dni_usuario='" + dni_usuario + '\'' +
                ", suscripciones=" + suscripciones +
                ", creados=" + creados +
                ", comentarios=" + comentarios +
                ", seguidores=" + seguidores +
                '}';
    }
}
