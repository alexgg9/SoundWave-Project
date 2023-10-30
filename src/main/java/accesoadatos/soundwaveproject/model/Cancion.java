package accesoadatos.soundwaveproject.model;

public class Cancion {
    private int id;
    private String nombre;
    private String duracion;
    private String genero;

    public Cancion(int id, String nombre, String duracion, String genero) {
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

    public String getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Duración: " + duracion + ", Género: " + genero;
    }

    public static void main(String[] args) {
        Cancion miCancion = new Cancion(1, "Mi Canción Favorita", "3:45", "Pop");
        System.out.println(miCancion);
    }
}

