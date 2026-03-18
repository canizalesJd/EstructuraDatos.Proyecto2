package Modelo;

/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 2 - Estructura de Datos
 * Descripción: Clase Articulos
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Maro 2026
 */
public class Articulo {
    // Atributos de la clase
    private int id;
    private String nombre;
    private String categoria;
    
    // Categorias
    public static final String[] CATEGORIAS = {
        "Ropa y accesorios",
        "Electrónica",
        "Hogar y muebles",
        "Belleza y cuidado personal",
        "Deportes y aire libre",
        "Juguetes y juegos",
        "Alimentos y bebidas"
    };
    
    // Constructor vacio
    public Articulo() {
        this.nombre = "";
        this.categoria = "";
    }
    
    // Constructor sobrecargado
    public Articulo(int id, String nombre, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }
    
    // Representacion Visual
    @Override
    public String toString() {
        return "Articulo(id: " + this.id + " | " + 
                "nombre: " + this.nombre + " | " +
                "categoria: " + this.categoria + ")";
    }
    
    // Getters y setters
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
