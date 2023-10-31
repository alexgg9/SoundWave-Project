package accesoadatos.soundwaveproject.model;

import java.sql.Time;
import java.util.Objects;

public class Cancion {
    private int id;
    private String nombre;
    private int duracion;
    private String genero;

    public Cancion(int id, String nombre, int duracion, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.genero = genero;
    }


	public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

	public void setId(int id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}

    @Override
	public int hashCode() {
		return Objects.hash(duracion, genero, id, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return Objects.equals(duracion, other.duracion) && Objects.equals(genero, other.genero) && id == other.id
				&& Objects.equals(nombre, other.nombre);
	}




	@Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Duración: " + duracion + ", Género: " + genero;
    }

   
}

