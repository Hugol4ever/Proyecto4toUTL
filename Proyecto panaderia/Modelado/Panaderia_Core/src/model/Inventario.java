
package model;

import java.util.Date;

/**
 *
 * @author hugo_
 */
public class Inventario {
    //Atributos
    private int idVenta;
    private int existencia;
    private Date fecha;
    private Panes pan;
    
    //Métodos
    
    //Constructor
    public Inventario() {    
    }

    public Inventario(int existencia, Date fecha, Panes pan) {
        this.existencia = existencia;
        this.fecha = fecha;
        this.pan = pan;
    }

    //Sección de Gettes y Settes
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Panes getPan() {
        return pan;
    }

    public void setPan(Panes pan) {
        this.pan = pan;
    }
    
}
