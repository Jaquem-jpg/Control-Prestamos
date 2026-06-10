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
    
    
}
