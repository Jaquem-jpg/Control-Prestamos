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
    
    
    
}
