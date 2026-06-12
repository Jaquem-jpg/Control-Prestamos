package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jaquem Obando González
 */
public class Tipo {
    private String nombre;
    private String descripcion;
    private List<Item> items;
   
    public Tipo(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.items = new ArrayList<>(); 
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

    public List<Item> getItems() {
        return items;
    }
    
   
    public void agregarItem(Item item) {
        if (item != null && !this.items.contains(item)) {
            this.items.add(item);
        }
    }
    
    public boolean eliminarItem(Item item) {
        return this.items.remove(item);
    }
}