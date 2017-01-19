package modelo.DAO;

/**
 *
 * @author Ozwaa
 */
public class DetalleVenta {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int idDetalleVenta;
    private int cantidad;
    private double precio;
    private Producto idProducto;
    private Venta idVenta;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public DetalleVenta() {
    }

    public DetalleVenta(int cantidad, double precio, Producto idProducto, Venta idVenta) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.idProducto = idProducto;
        this.idVenta = idVenta;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos get y set">
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }
    //</editor-fold>

}