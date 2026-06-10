package logica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaquem Obando González
 */
public class Usuario {
    private String email;
    private String nombre;
    private String telefono;
    private List<Prestamo> prestamos; 
    

    //Constructor
    public Usuario(String email, String nombre, String telefono) {
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
        this.prestamos = new ArrayList<>(); // Iniciamos la lista de prestamos vacía.
    }
    
    //Getters y Setters

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    
   
    
    //  Agregar Prestamo a un Usario
    public void agregarPrestamo(Prestamo prestamo){
        if (prestamo != null && !this.prestamos.contains(prestamo)) {
            this.prestamos.add(prestamo);
        }
    }
     
    // Eliminar un prestamo 
    public boolean eliminarPrestamo(Prestamo prestamo){
        return this.prestamos.remove(prestamo);
        
    }
    
    
    
    
}
