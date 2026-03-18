package Modelo;


/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 2 - Estructura de Datos
 * Descripción: Entidad principal del sistema
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Maro 2026
 */
public class Departamento {
    // Atributos de la clase
    private int id;
    private String nombre;
    private Articulo[] articulos;
    private int contadorArticulos;
    
    
    // Constructor vacio
    public Departamento() {
        this.nombre = "";
        this.articulos = new Articulo[20];
    }
    
    // Representacion Visual
    @Override
    public String toString() {
        return "Departamento(id: " + this.id + " | " + 
                "nombre: " + this.nombre + ")";
    }
    
    // Constructor sobrecargado
    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.articulos = new Articulo[20];
        this.contadorArticulos = 0;
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
    
    public Articulo[] getArticulos() {
        return articulos;
    }
    
    public void setArticulos(Articulo[] articulos) {
        this.articulos = articulos;
    }
    
    public int getContadorArticulos() {
        return contadorArticulos;
    }
    
    public void setContadorArticulos(int contadorArticulos) {
        this.contadorArticulos = contadorArticulos;
    }
}
