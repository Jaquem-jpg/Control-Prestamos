package logica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaquem Obando González
 */
public class Categoria {
    private String nombre;
    private String descripcion;
    private List<Item> items; 
    
    // Constructor
    public Categoria(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.items = new ArrayList<>(); // Inicializamos la lista vacía
    }
    
    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Item> getItems() {
        return items;
    }
    
    
    
    
    // Métodos bidireccionales 
    public void agregarItem(Item item) {
        if (item != null && !this.items.contains(item)) {
            this.items.add(item);
        }
    }
    
    public boolean eliminarItem(Item item) {
        return this.items.remove(item);
    }
}