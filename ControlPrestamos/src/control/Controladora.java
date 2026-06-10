package control;

//Imports necesarios
import logica.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaquem Obando González
 */
public class Controladora {
    //Listas goblales que se guardan en memoria
    private List<Usuario> usuarios;
    private List<Item> items;
    private List<Categoria> categorias;
    private List<Tipo> tipos;
    private List<Prestamo> prestamos;
    
    //Constructor de la controladora
    public Controladora(){
        this.usuarios = new ArrayList<>();
        this.items = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.tipos = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }
    
    // Métodos de búsqueda auxiliares
    public Usuario buscarUsuario(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        return null;
    }

    public Item buscarItem(String codigo) {
        for (Item i : items) {
            if (i.getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }
        return null;
    }
    
    //Métodos para agregar
  
    public Categoria agregarCategoria(String codigo, String descripcion) {
        Categoria nueva = new Categoria(codigo, descripcion);
        categorias.add(nueva);
        return nueva;
    }

    public Tipo agregarTipo(String codigo, String descripcion) {
        Tipo nuevo = new Tipo(codigo, descripcion);
        tipos.add(nuevo);
        return nuevo;
    }

    public Item agregarItem(String codigo, String nombre, String descripcion, Tipo tipo) {
        Item nuevo = new Item(codigo, nombre, descripcion, tipo);
        items.add(nuevo);
        return nuevo;
    }
    
    // Métodos encargados de eliminar
    
    public boolean eliminarUsuario(Usuario usuario) {
        if (usuario == null) return false;
        
        // REGLA: No se puede borrar si tiene préstamos activos
        for (Prestamo p : usuario.getPrestamos()) {
            if (!p.estaFinalizado()) {
                return false; 
            }
        }
        return usuarios.remove(usuario);
    }

    public boolean eliminarItem(Item item) {
        if (item == null) return false;
        
        // No se puede borrar si está prestado actualmente
        if (item.estaPrestado()) {
            return false;
        }
        return items.remove(item);
    }

    public boolean eliminarTipo(Tipo tipoARemover) {
    if (tipoARemover == null) return false;

    // Si se borra un tipo, los ítems asociados pasan a uno "Genérico"
    Tipo tipoGenerico = null;
    for (Tipo t : tipos) {
        // Buscamos si ya existe un tipo con nombre "Generico"
        if (t.getNombre().equalsIgnoreCase("Generico") || t.getNombre().equalsIgnoreCase("Genérico")) {
            tipoGenerico = t;
            break;
        }
    }
    
    // Si no existe el Tipo Genérico, se crea
    if (tipoGenerico == null) {
        tipoGenerico = new Tipo("Generico", "Tipo asignado a ítems cuyo tipo original fue eliminado");
        tipos.add(tipoGenerico);
    }

   
    for (Item item : items) {
        if (item.getTipo().equals(tipoARemover)) {
            item.setTipo(tipoGenerico);
        }
    }
    return tipos.remove(tipoARemover);
}
    
    
    
}
