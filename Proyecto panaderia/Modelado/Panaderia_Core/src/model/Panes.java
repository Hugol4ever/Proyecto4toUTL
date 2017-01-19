
package model;

/**
 *
 * @author hugo_
 */
public class Panes {
    //Atributos
    private int codPan;
    private String descripcion;
    private double precioP;
    private double precioV;
    
    //Métodos
    
    //Constructor
    public Panes() {    
    }

    public Panes(String descripcion, double precioP, double precioV) {
        this.descripcion = descripcion;
        this.precioP = precioP;
        this.precioV = precioV;
    }

    public Panes(int codPan, String descripcion, double precioP, double precioV) {
        this.codPan = codPan;
        this.descripcion = descripcion;
        this.precioP = precioP;
        this.precioV = precioV;
    }

    //Sección de Gettes y Settes
    public int getCodPan() {
        return codPan;
    }

    public void setCodPan(int codPan) {
        this.codPan = codPan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioP() {
        return precioP;
    }

    public void setPrecioP(double precioP) {
        this.precioP = precioP;
    }

    public double getPrecioV() {
        return precioV;
    }

    public void setPrecioV(double precioV) {
        this.precioV = precioV;
    }
    
}