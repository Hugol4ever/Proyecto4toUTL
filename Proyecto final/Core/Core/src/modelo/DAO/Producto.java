package modelo.DAO;

/**
 *
 * @author Ozwaa
 */
public class Producto {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int idProducto;
    private String nombre;
    private String marca;
    private String categoria;
    private int existencia;
    private double precio;
    private String foto;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Producto() {
    }

    public Producto(String nombre, String marca, String categoria, int existencia, double precio, String foto) {
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.existencia = existencia;
        this.precio = precio;
        this.foto = foto;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos get y set">
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    //</editor-fold>

}