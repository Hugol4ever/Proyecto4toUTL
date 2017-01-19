
package model;

import java.util.Date;

/**
 *
 * @author hugo_
 */
public class Ventas {
    //Atributos
    private int folio;
    private Date fecha;
    private double total;
    
    //Métodos
    
    //Constructor
    public Ventas() {    
    }

    public Ventas(Date fecha, double total) {
        this.fecha = fecha;
        this.total = total;
    }

    //Sección de Gettes y Settes
    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
