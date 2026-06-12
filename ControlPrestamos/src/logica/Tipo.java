package logica;

// imports necesarios
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jaquem Obando González
 */
public class Tipo {
    private String descripcion;
    private String nombre;
    private List<Item> items;
   
    
    
    //Constructor 
    public Tipo(String descripcion, String nombre){
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.items = new ArrayList<>();
    }
    
    //Getters y Setters

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
   
    
    
    
    
    
}
