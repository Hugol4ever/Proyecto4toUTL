
package modelo.DTO;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ConnectionMysql;
import modelo.DAO.Cliente;

/**
 *
 * @author hugol
 */
public class DTOHuella {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private ConnectionMysql conexion;
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPFeatureSet featuresverificacion;
    public static String TEMPLATE_PROPERTY="template";
    private DPFPTemplate template;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos generales">
    public Cliente huella() {
        Cliente cliente = new Cliente();
        try {
            Connection con = this.conexion.abrir();
            PreparedStatement verificarStmt = con.prepareStatement("Select * from datosCliente");
            ResultSet rs = verificarStmt.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                byte templateBuffer[] = rs.getBytes("Huella");
                DPFPTemplate referenceTemplate=DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //
                DPFPTemplate old = referenceTemplate;
                this.template = referenceTemplate;
                //firePropertyChange(TEMPLATE_PROPERTY, old, template);
                //
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, this.template);
                if (result.isVerified()) {
                    cliente.setNombre(rs.getString("Nombre"));
                    cliente.setIdCliente(rs.getInt("Id_Cliente"));
                    JOptionPane.showMessageDialog(null, "Cliente " + cliente.getNombre() + " encontrado");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DTOHuella.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public DTOHuella(DPFPFeatureSet featuresverificacion, DPFPTemplate template) {
        this(new ConnectionMysql());
        this.featuresverificacion = featuresverificacion;
        this.template = template;
    }

    public DTOHuella(ConnectionMysql conexion) {
        this.conexion = conexion;
    }
    //</editor-fold>

}