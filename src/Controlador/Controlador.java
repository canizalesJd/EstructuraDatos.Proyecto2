package Controlador;
import Modelo.Departamento;
import java.util.InputMismatchException;


/*
 * Universidad Estatal a Distancia (UNED)
 * Cuatrimestre: I Cuatrimestre 2026
 * Proyecto: Proyecto 2 - Estructura de Datos
 * Descripción: Clase controlador del sistema
 * Estudiante: Jose David Canizales Azocar
 * Fecha: Maro 2026
 */
public class Controlador {
    // Pila de Departamentos (LIFO)
    private Departamento[] departamentos;
    private int contadorDepartamentos;
    
    // Contadores
    private int contadorDepartamentosId;
    private int contadorArticulosGlobal;
    
    // Constructor
    public Controlador() {
        this.departamentos = new Departamento[20];
        this.contadorDepartamentos = 0;
        this.contadorDepartamentosId = 0;
        this.contadorArticulosGlobal = 0;
    }
    
    // Metodo para Agregar departamento
    public void agregarDepartamento(String nombre) {
        // Validar capacidad de la pila
        if (contadorDepartamentos >= 20) {
            throw new IllegalStateException(
                "Error, listado de departamentos lleno"
            );
        }
        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new InputMismatchException(
                "Error, nombre no debe estar vacio"
            );
        }
        // Crear nuevo departamento
        contadorDepartamentosId++;
        Departamento nuevo = new Departamento(contadorDepartamentosId, nombre);
        
        // Agregar el nuevo Departamento
        departamentos[contadorDepartamentos] = nuevo;
        contadorDepartamentos++;
    }
    
    // Metodo para obtener el ultimo departamento (peek)
    public Departamento peekDepartamentos() {
        if (contadorDepartamentos == 0) {
            throw new IllegalStateException(
                "Error, no hay departamentos registrados"
            );
        }
        
        return departamentos[contadorDepartamentos -1];
    }
    
    // Metodo para eliminar departamento
    public void eliminarDepartamento() {
        // Metodo para remover departamento
        if (contadorDepartamentos == 0) {
            throw new IllegalStateException(
                "Error, no hay departamentos por eliminar"
            );
        }
        
        Departamento ultimo = peekDepartamentos();
       
        // Validar que este vacio el departamento
        if (ultimo.getContadorArticulos() != 0) {
            throw new IllegalStateException(
                "Error, no se puede eliminar un departamento con articulos"
            );
        }
        
        // Eliminar y reducir contador
        departamentos[contadorDepartamentos - 1] = null;
        contadorDepartamentos --;
    }
    
    // Metodo para obtener el listado actual de departamentos (sin null)
    public Departamento[] obtenerDepartamentosActuales() {
        Departamento[] resultado = new Departamento[contadorDepartamentos];
        // Referencia: https://docs.oracle.com/javase/7/docs/api/java/lang/System.html#arraycopy(java.lang.Object,%20int,%20java.lang.Object,%20int,%20int)
        // Copia a nuevo array del tamano del contador, para no obtener los nulos
        System.arraycopy(departamentos, 0, resultado, 0, contadorDepartamentos);
        return resultado;
    }
    
    // Metodo para retornar departamento por ID
    public Departamento obtenerDepartamentoById(int id) {
        for (int i = 0; i < contadorDepartamentos; i++) {
            if (departamentos[i].getId() == id) {
                return departamentos[i];
            }
        }
        return null; // Nulo si no se encuentra ningun departamento
    }
}
