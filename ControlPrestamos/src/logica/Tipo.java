package logica;

/**
 *
 * @author Jaquem Obando González
 */
public class Tipo {
    private String descripcion;
    private String nombre;
   
    
    
    //Constructor 
    public Tipo(String descripcion, String nombre){
        this.descripcion = descripcion;
        this.nombre = nombre;
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
