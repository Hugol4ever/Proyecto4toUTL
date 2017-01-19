package modelo.DAO;

/**
 *
 * @author Ozwaa
 */

public class Cliente {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int idCliente;
    private String nombre;
    private String correo;
    private String telefono;
    private String genero;
    private String nTarjeta;
    private Usuario idUsuario;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Cliente() {
    }

    public Cliente(String nombre, String correo, String telefono, String genero, String nTarjeta, Usuario idUsuario) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.nTarjeta = nTarjeta;
        this.idUsuario = idUsuario;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos get y set">
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    /**
     * Método que genera la serie de números aleatorios
     * @param idUsuario es el id del usuario
     */
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getnTarjeta() {
        return nTarjeta;
    }

    public void setnTarjeta(String nTarjeta) {
        this.nTarjeta = nTarjeta;
    }
    //</editor-fold>
    
}