package logica;

import java.util.Date;

/**
 *
 * @author Jaquem Obando González
 */
public class Alerta {
    private String mensaje;
    private boolean recurrente;
    private int intervaloDias;
    private Date fechaActivacion;
    private Prestamo prestamo; // Para que Alerta conozca ha que préstamo pertenece.
    
    // Constructor
    public Alerta(String mensaje, boolean recurrente, int intervaloDias, Date fechaActivacion, Prestamo prestamo) {
        this.mensaje = mensaje;
        this.recurrente = recurrente;
        this.intervaloDias = intervaloDias;
        this.fechaActivacion = fechaActivacion;
        this.prestamo = prestamo;
    }

    // Lógica para verificar si se debe mostrar en una fecha específica
    public boolean debeMostrarse(Date fechaActual) {
        if (fechaActual == null || fechaActivacion == null) {
            return false;
        }
        // Si ya pasó la fecha de activación o coincide exactamente
        return !fechaActual.before(fechaActivacion);
    }

    // Getters y Setters
    public String getMensaje() {
        return mensaje;
    }

    public boolean esRecurrente() {
        return recurrente;
    }

    public int getIntervaloDias() {
        return intervaloDias;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }

    public void setIntervaloDias(int intervaloDias) {
        this.intervaloDias = intervaloDias;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    
    
    
}
