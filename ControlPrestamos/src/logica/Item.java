package logica;

//Imports necesarios
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaquem Obando González
 */
public class Item {
    private String codigo;
    private String descripcion;
    private String nombre;
    private Tipo tipo; 
    private List<Categoria> categorias; 
    private Prestamo prestamoActual; // Sirve para guardar el préstamo actual si está ocupado.
    
    //Constructor
    public Item(String codigo, String nombre, String descripcion, Tipo tipo){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.categorias = new ArrayList<>();
        this.prestamoActual = null; // Al momento de crearse, se inicia vació para que este disponible.
    }   
    
    
    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public Prestamo getPrestamoActual() {
        return prestamoActual;
    }

    public void setPrestamoActual(Prestamo prestamoActual) {
        this.prestamoActual = prestamoActual;
    }
    
    
    
    
    
    //Agregar una Categoria
    public void agregarCategoria(Categoria categoria) {
        if (categoria != null && !this.categorias.contains(categoria)) {
            this.categorias.add(categoria);
        }
    }
    
     //Eliminar una Categoria
    public void eliminarCategoria(Categoria categoria) {
        this.categorias.remove(categoria);
    }
    
    // Conocer si esta prestada
    public boolean estaPrestado() {
        return this.prestamoActual != null;
    }
    
    
    
}
