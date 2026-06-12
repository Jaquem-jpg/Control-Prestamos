package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jaquem Obando González
 */
public class Item {
    private String codigo;
    private String nombre;
    private String descripcion;
    private Tipo tipo; 
    private List<Categoria> categorias; 
    private Prestamo prestamoActual; 
    
    // Constructor 
    public Item(String codigo, String nombre, String descripcion, Tipo tipo){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categorias = new ArrayList<>();
        this.prestamoActual = null; 
        
        // Amarre bidireccional automático s
        setTipo(tipo); 
    }   
    
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

   
    public final void setTipo(Tipo nuevoTipo) {
        // Si ya tenía un tipo antes, nos removemos de su lista para no dejar basura
        if (this.tipo != null) {
            this.tipo.eliminarItem(this);
        }
        
        this.tipo = nuevoTipo;
        
        // Le avisamos al nuevo Tipo que nos agregue usando 'this'
        if (nuevoTipo != null) {
            nuevoTipo.agregarItem(this);
        }
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
    
    
    public void agregarCategoria(Categoria categoria) {
        if (categoria != null && !this.categorias.contains(categoria)) {
            this.categorias.add(categoria);
            categoria.agregarItem(this); // Categoría guarda este ítem
        }
    }
    
    public void eliminarCategoria(Categoria categoria) {
        if (categoria != null && this.categorias.contains(categoria)) {
            this.categorias.remove(categoria);
            categoria.eliminarItem(this); // La categoría  remueve este ítem
        }
    }
    
    public boolean estaPrestado() {
        return this.prestamoActual != null;
    }
}