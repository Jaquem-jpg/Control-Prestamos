
package logica;

/**
 *
 * @author Jaquem Obando González
 */
public class Usuario {
    private String email;
    private String nombre;
    private String telefono;

    //Constructor
    public Usuario(String email, String nombre, String telefono) {
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
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
    
    
    
    
    
    
}
