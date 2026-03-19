package Controlador;
import Modelo.Articulo;
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
    
    // ================ Metodos de Departamentos ================
    
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
    public Departamento buscarDepartamento(int id) {
        for (int i = 0; i < contadorDepartamentos; i++) {
            if (departamentos[i].getId() == id) {
                return departamentos[i];
            }
        }
        return null; // Nulo si no se encuentra ningun departamento
    }
    
    // ================ Metodos de Artitulos ================
    
    // Metodo para agregar un articulo al departamento
    public void agregarArticulo(
        int idDepartamento, 
        String nombre, 
        String categoria
    ) {
        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new InputMismatchException(
                "Error, nombre no debe estar vacio"
            );
        }
        
        // Validar departamento
        Departamento departamento = buscarDepartamento(idDepartamento);
        if (departamento == null) {
            throw new IllegalStateException(
                "Error, departamento no encontrado"
            );
        }
        
        // Validar espacio en la cola
        if (departamento.getContadorArticulos() >= 20) {
            throw new IllegalStateException(
                "Error, no hay espacio en la cola del departamento"
            );
        }
        
        // Actualizar ID global de articulo
        contadorArticulosGlobal++;
        
        // Crear objeto de tipo articulo
        Articulo nuevo = new Articulo(
            contadorArticulosGlobal, 
            nombre, 
            categoria
        );
        
        // Agregar a la cola 
        
        int contador = departamento.getContadorArticulos();
        departamento.getArticulos()[contador] = nuevo;
        departamento.setContadorArticulos(contador + 1);
    }
    
    // Metodo para eliminar un articulo del departamento
    public void eliminarArticulo(int idDepartamento) {
        // Validar departamento
        Departamento departamento = buscarDepartamento(idDepartamento);
        if (departamento == null) {
            throw new IllegalStateException(
                "Error, departamento no encontrado"
            );
        }
        
        // Validar que la cola no este vacia
        int contador = departamento.getContadorArticulos();
        
        if (contador == 0) {
            throw new IllegalStateException(
                "Error: cola vacia, no se puede eliminar"
            );
        }
        
        // Eliminar primer articulo y desplazar posiciones (dequeue)
        Articulo[] articulos = departamento.getArticulos();
        
        // Ref: https://www.geeksforgeeks.org/dsa/array-implementation-of-queue-simple/
        for (int i = 1; i < contador; i++) {
            articulos[i-1] = articulos[i];
        }
        
        // Actualizar contador
        departamento.setContadorArticulos(contador - 1);
    }
    
    // Metodo para el traslado de articulos entre dos departamentos
    public void trasladarArticulos(
        int departamentoOrigen, 
        int departamentoDestino
    ) {
        // Validar que hayan 2 departamentos o mas
        if (contadorDepartamentos < 2) {
            throw new IllegalStateException(
                "Error, deben haber almenos un departamento mas para el traslado"
            );
        }
        
        Departamento origen = buscarDepartamento(departamentoOrigen);
        Departamento destino = buscarDepartamento(departamentoDestino);
        
        // Validar que los departamentos existan
        if (origen == null) {
            throw new IllegalStateException(
                "Error, departamento de origen no encontrado"
            );
        }
        
        if (destino == null) {
            throw new IllegalStateException(
                "Error, departamento de destino no encontrado"
            );
        }
        
        // Validar que el origen sea diferente al destino
        if (origen == destino) {
            throw new IllegalStateException(
                "Error, traslado invalido. Debe seleccionar un destino diferente"
            );
        }
        
        // Validar que exista el menos un articulo en el departamento de origen
        if (origen.getContadorArticulos() == 0) {
             throw new IllegalStateException(
                "Error, no hay articulos para trasladar"
            );
        }
        
        // Validar que haya suficiente espacio
        int espacioDisponible = 20 - destino.getContadorArticulos();
        int articulosATrasladar = origen.getContadorArticulos();

        if (articulosATrasladar > espacioDisponible) {
            throw new IllegalStateException(
                "Error, no hay suficiente espacio en el departamento destino"
            );
        }
        
        // Mover articulos de la cola de origen a la cola de destino
        while (origen.getContadorArticulos() > 0) {         
            // Obtener primer articulo del departamento
            Articulo articulo = origen.getArticulos()[0];
           
            // Eliminar del origen
            eliminarArticulo(origen.getId());
            
            // Agregar en la cola de destino
            agregarArticulo(
                destino.getId(), 
                articulo.getNombre(),
                articulo.getCategoria()
            );
        }
    }
}
