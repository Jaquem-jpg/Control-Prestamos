package logica;

// Imports necesarios
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaquem Obando González
 */
public class Prestamo {
    private int id;
    private Date fechaInicio;
    private Date fechaLimite;
    private Date fechaRetorno;
    private Usuario usuario;  // Usuario al que se le presta 
    private List<Item> items; // Lista de ítems.
    private Alerta alerta; // Alerta 
    
    //Constructor

    public Prestamo(int id, Usuario usuario, Date fechaInicio) {
        this.id = id;
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.items = new ArrayList<>();
        this.fechaLimite = null;
        this.fechaRetorno = null;
        this.alerta = null;
    }
    
    
    //Getters y Setters 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaRetorno() {
        return fechaRetorno;
    }

    public void setFechaRetorno(Date fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Item> getItems() {
        return items;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }
    
    
    
    
    //Agregar Item
   public void agregarItem(Item item) {
        if (item != null && !this.items.contains(item)) {
            this.items.add(item);
            item.setPrestamoActual(this); // Marcamos el ítem como ocupado
        }
    }
   
   //Retornar un item
   public boolean retornarItem(Item item) {
        if (this.items.remove(item)) {
            item.setPrestamoActual(null); // Liberamos el ítem individualmente
            return true;
        }
        return false;
    }
   
   
   // Finalizar un prestamo
   public boolean estaFinalizado() {
        return this.fechaRetorno != null;
    }
    
    
    
}
