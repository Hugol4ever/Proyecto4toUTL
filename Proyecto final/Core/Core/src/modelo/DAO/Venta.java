package modelo.DAO;

import java.util.Date;

/**
 *
 * @author Ozwaa
 */
public class Venta {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int idVenta;
    private Date fecha;
    private Date hora;
    private Cliente idCliente;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Venta() {
    }

    public Venta(Date fecha, Date hora, Cliente idCliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos get y set">
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
    //</editor-fold>

}