package modelo.DAO;

/**
 *
 * @author Ozwaa
 */
public class Usuario {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int idUsuario;
    private String nomUsuario;
    private String contraseña;
    private Huella idHuella;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Usuario() {
    }

    public Usuario(String nomUsuario, String contraseña, Huella idHuella) {
        this.nomUsuario = nomUsuario;
        this.contraseña = contraseña;
        this.idHuella = idHuella;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos get y set">
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Huella getIdHuella() {
        return idHuella;
    }

    public void setIdHuella(Huella idHuella) {
        this.idHuella = idHuella;
    }
    //</editor-fold>

}