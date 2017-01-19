
package model;

/**
 *
 * @author hugo_
 */
public class Detalle {
    //Atributos
    private int idDetalle;
    private int cantidad;
    private double importe;
    private Panes panes;
    private Ventas folio;
    
    //Métodos
    
    //Constructor
    public Detalle() {    
    }

    public Detalle(int cantidad, double importe, Panes panes, Ventas folio) {
        this.cantidad = cantidad;
        this.importe = importe;
        this.panes = panes;
        this.folio = folio;
    }

    //Sección de Gettes y Settes
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Panes getPanes() {
        return panes;
    }

    public void setPanes(Panes panes) {
        this.panes = panes;
    }

    public Ventas getFolio() {
        return folio;
    }

    public void setFolio(Ventas folio) {
        this.folio = folio;
    }
}
