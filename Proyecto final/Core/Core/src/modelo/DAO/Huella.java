package modelo.DAO;

import com.digitalpersona.onetouch.DPFPTemplate;

/**
 *
 * @author Ozwaa
 */
public class Huella {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int idHuella;
    private DPFPTemplate huella;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Huella() {
    }

    public Huella(DPFPTemplate template) {
        this.huella = template;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos get y set">
    public DPFPTemplate getTemplate() {
        return huella;
    }

    public void setTemplate(DPFPTemplate template) {
        this.huella = template;
    }
    
    public int getIdHuella() {
        return idHuella;
    }

    public void setIdHuella(int idHuella) {
        this.idHuella = idHuella;
    }
    //</editor-fold>

}