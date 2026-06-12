package control;

//Imports necesarios
import logica.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @author Jaquem Obando González
 */
public class Controladora {
    
    private List<Usuario> usuarios;
    private List<Item> items;
    private List<Categoria> categorias;
    private List<Tipo> tipos;
    private List<Prestamo> prestamos;
    
    // Constructor de la controladora
    public Controladora(){
        this.usuarios = new ArrayList<>();
        this.items = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.tipos = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }
    
    //Métodos de busqueda
    public Usuario buscarUsuario(String email) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("Error: El email de busqueda no puede estar vacio.");
        }
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email.trim())) {
                return u;
            }
        }
        throw new Exception("Error: No se encontro ningun usuario con el email: " + email);
    }

    public Item buscarItem(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new Exception("Error: El codigo de busqueda no puede estar vacio.");
        }
        for (Item i : items) {
            if (i.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return i;
            }
        }
        throw new Exception("Error: No se encontro ningun item con el codigo: " + codigo);
    }

    private Tipo buscarTipoInterno(String nombre) throws Exception {
        for (Tipo t : tipos) {
            if (t.getNombre().equalsIgnoreCase(nombre.trim())) {
                return t;
            }
        }
        throw new Exception("Error: No se encontro el Tipo '" + nombre + "'.");
    }

    private Prestamo buscarPrestamoInterno(int id) throws Exception {
        for (Prestamo p : prestamos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new Exception("Error: No se encontro ningun prestamo con el ID: " + id);
    }
    
    //Métodos para agregar y registrar
  
    public void registrarUsuario(String email, String nombre, String telefono) throws Exception {
        if (email == null || email.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Error: El email y el nombre son obligatorios.");
        }
        // Evitar duplicados por email
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email.trim())) {
                throw new Exception("Error: Ya existe un usuario registrado con el email: " + email);
            }
        }
        usuarios.add(new Usuario(email.trim(), nombre.trim(), telefono.trim()));
    }

    public void agregarCategoria(String nombre, String descripcion) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Error: El nombre de la categoria no puede estar vacio.");
        }
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre.trim())) {
                throw new Exception("Error: La categoria '" + nombre + "' ya existe.");
            }
        }
        categorias.add(new Categoria(nombre.trim(), descripcion.trim()));
    }

    public void agregarTipo(String nombre, String descripcion) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Error: El nombre del tipo no puede estar vacio.");
        }
        for (Tipo t : tipos) {
            if (t.getNombre().equalsIgnoreCase(nombre.trim())) {
                throw new Exception("Error: El tipo '" + nombre + "' ya existe.");
            }
        }
        tipos.add(new Tipo(nombre.trim(), descripcion.trim()));
    }

  
    public void agregarItem(String codigo, String nombre, String descripcion, String nombreTipo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Error: El codigo y el nombre del item son obligatorios.");
        }
        // Validar duplicado de código
        for (Item i : items) {
            if (i.getCodigo().equalsIgnoreCase(codigo.trim())) {
                throw new Exception("Error: Ya existe un item registrado con el codigo: " + codigo);
            }
        }
        
        Tipo tipoAsociado = buscarTipoInterno(nombreTipo);
        items.add(new Item(codigo.trim(), nombre.trim(), descripcion.trim(), tipoAsociado));
    }
    
   //Métodos para eliminar
    
    public void eliminarUsuario(String email) throws Exception {
        Usuario usuario = buscarUsuario(email);
        
        //  No se puede borrar si tiene préstamos activos
        for (Prestamo p : usuario.getPrestamos()) {
            if (!p.estaFinalizado()) {
                throw new Exception("Error: El usuario no puede ser eliminado porque tiene prestamos activos."); 
            }
        }
        usuarios.remove(usuario);
    }

    public void eliminarItem(String codigo) throws Exception {
        Item item = buscarItem(codigo);
        
        // No se puede borrar si está prestado actualmente
        if (item.estaPrestado()) {
            throw new Exception("Error: El item con codigo '" + codigo + "' no se puede eliminar porque esta prestado.");
        }
        
        // Si el ítem se borra, hay que removerlo de su tipo.
        if (item.getTipo() != null) {
            item.getTipo().eliminarItem(item);
        }
        items.remove(item);
    }

    public void eliminarTipo(String nombreTipo) throws Exception {
        Tipo tipoARemover = buscarTipoInterno(nombreTipo);

        // Si se borra un tipo, buscamos o creamos el "Generico"
        Tipo tipoGenerico = null;
        for (Tipo t : tipos) {
            if (t.getNombre().equalsIgnoreCase("Generico") || t.getNombre().equalsIgnoreCase("Generico")) {
                tipoGenerico = t;
                break;
            }
        }
        
        if (tipoGenerico == null) {
            tipoGenerico = new Tipo("Generico", "Tipo asignado a items cuyo tipo original fue eliminado");
            tipos.add(tipoGenerico);
        }

        if (tipoARemover.equals(tipoGenerico)) {
            throw new Exception("Error: No se permite eliminar el Tipo Generico del sistema.");
        }

        // Modificamos los ítems asociados pasándolos al genérico.
        for (Item item : items) {
            if (item.getTipo().equals(tipoARemover)) {
                item.setTipo(tipoGenerico);
            }
        }
        tipos.remove(tipoARemover);
    }
    
    // Funcionalidades de un prestamo
    
    public int crearPrestamo(String emailUsuario, Date fechaInicio) throws Exception {
        Usuario usuario = buscarUsuario(emailUsuario);
        if (fechaInicio == null) {
            throw new Exception("Error: La fecha de inicio del prestamo no es válida.");
        }
        
        int nuevoId = prestamos.size() + 1; 
        Prestamo nuevoPrestamo = new Prestamo(nuevoId, usuario, fechaInicio);
        prestamos.add(nuevoPrestamo);
        
        usuario.agregarPrestamo(nuevoPrestamo);
        return nuevoId; // Retornamos el ID.
    }

    public void agregarItemAPrestamo(int idPrestamo, String codigoItem) throws Exception {
        Prestamo prestamo = buscarPrestamoInterno(idPrestamo);
        Item item = buscarItem(codigoItem);
        
        if (item.estaPrestado()) {
            throw new Exception("Error: El item '" + item.getNombre() + "' ya se encuentra ocupado en otro prestamo.");
        }
        
        prestamo.agregarItem(item); 
    }

    public void finalizarPrestamo(int idPrestamo) throws Exception {
        Prestamo prestamo = buscarPrestamoInterno(idPrestamo);
        if (prestamo.estaFinalizado()) {
            throw new Exception("Error: Este prestamo ya fue finalizado previamente.");
        }
        
        prestamo.setFechaRetorno(new Date()); 
        
        // Liberar todos los ítems de este préstamo de forma segura
        for (Item item : prestamo.getItems()) {
            item.setPrestamoActual(null);
        }
        
        if (prestamo.getAlerta() != null) {
            prestamo.setAlerta(null);
        }
    }
    
    //Funcionalidades de una Alerta
    
    public void crearAlerta(String mensaje, boolean recurrente, int intervaloDias, Date fechaActivacion, int idPrestamo) throws Exception {
        Prestamo prestamo = buscarPrestamoInterno(idPrestamo);
        if (fechaActivacion == null) {
            throw new Exception("Error: La fecha de activacion de la alerta es obligatoria.");
        }
        
        Alerta nuevaAlerta = new Alerta(mensaje, recurrente, intervaloDias, fechaActivacion, prestamo);
        prestamo.setAlerta(nuevaAlerta);
    }

    public List<Item> listarItemsDisponibles() {
        List<Item> disponibles = new ArrayList<>();
        for (Item item : items) {
            if (!item.estaPrestado()) {
                disponibles.add(item);
            }
        }
        return disponibles;
    }

    public int listarPrestamosActivos() {
        int contador = 0;
        for (Prestamo p : prestamos) {
            if (!p.estaFinalizado()) {
                contador++;
            }
        }
        return contador;
    }
    
    
    public List<Usuario> listarUsuarios() { return this.usuarios; }
    public List<Item> listarItems() { return this.items; }
    public List<Categoria> listarCategorias() { return this.categorias; }
    public List<Tipo> listarTipos() { return this.tipos; }

    //Métodos de reportes
 
    public String generarReportePorCategoria() {
        return "Reporte por Categoraas - Pendiente formatear orden alfabetico";
    }

    public String generarReportePorItem() {
        return "Reporte por Items - Pendiente formatear orden alfabetico";
    }

    public String generarReportePorTipo() {
        return "Reporte por Tipos - Pendiente formatear orden alfabetico";
    }

    public String generarReportePorUsuarios() {
        return "Reporte por Usuarios - Pendiente formatear orden alfabetico";
    }
}