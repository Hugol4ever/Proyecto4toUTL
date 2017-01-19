package controlador;

import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.Participante;
import modelo.DTO.ControladorRegistro;
import modelo.DTO.PDF;
import mprlib.MprManager;
import vistas.Impresiones;

/**
 *
 * @author hugol
 */
public class ImpresionesController implements ActionListener {
    
    private MprManager mpr;

    private Impresiones impresiones;
    private DefaultTableModel modeloG;
    private DefaultTableModel modeloC;

    public void iniciarVista() {
        iniciarTabla();
    }

    private void iniciarTabla() {
        configurarTablaG();
        cargarTablaGafete();
        configurarTablaC();
        cargarTablaConstancia();
    }

    private void configurarTablaG() {
        this.modeloG = new DefaultTableModel();
        this.modeloG.addColumn("Matricula");
        this.modeloG.addColumn("Nombre");
        this.modeloG.addColumn("UT");
        this.impresiones.getTblGafete().setModel(this.modeloG);
    }
    
    private void configurarTablaC() {
        this.modeloC = new DefaultTableModel();
        this.modeloC.addColumn("Matricula");
        this.modeloC.addColumn("Nombre");
        this.impresiones.getTblConstancias().setModel(this.modeloC);
    }

    private void grabarGafete(String id, MprManager mpr) throws Exception {
        this.mpr = mpr;
        byte[] bytes = id.getBytes();
        System.out.println(bytes);
        mpr.setByteSeparator("-");
        if (!mpr.connect()) 
            throw new Exception("No se pudo iniciar la comunicación por el puerto");
        
        while (!mpr.c1g2_WriteID(bytes, 0, bytes.length)) 
            System.out.println("Intentando escribir dato");
            System.out.println("La grabación fue todo un éxito");
            mpr.close();
            JOptionPane.showMessageDialog(null, "Grabación exitosa");
    }

    private void cargarTablaGafete() {
        ControladorRegistro cr = new ControladorRegistro();
        try {
            ArrayList<Participante> lista = cr.busquedaParticipante();
            for (Participante lista1 : lista) {
                Object rowData[] = {lista1.getClave(), lista1.getNombre(), lista1.getUtl_Procedencia()};
                this.modeloG.addRow(rowData);
            }
        } catch (Exception ex) {
            Logger.getLogger(ImpresionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaConstancia() {
        ControladorRegistro cr = new ControladorRegistro();
        try {
            ArrayList<Participante> lista = cr.participante2();
            for (Participante lista1 : lista) {
                Object rowData[] = {lista1.getClave(), lista1.getNombre()};
                this.modeloC.addRow(rowData);
            }
        } catch (Exception ex) {
            Logger.getLogger(ImpresionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.impresiones.getBtnGrabar() == e.getSource()) {
            String puerto = "COM5";

            MprManager foxManager = new MprManager(puerto, 9600);
            String id = this.impresiones.getTblGafete().getValueAt(this.impresiones.getTblGafete().getSelectedRow(), 0).toString();
            try {
                grabarGafete(id, foxManager);
            } catch (Exception ex) {
                Logger.getLogger(ImpresionesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            int rows = this.impresiones.getTblConstancias().getRowCount();
            String[] participante = new String[rows];
            for (int i = 0; i < rows; i++) {
                participante[i] = this.impresiones.getTblConstancias().getValueAt(i, 1).toString();
            }
            PDF p = new PDF();
            try {
                p.crearPDF(participante);
                p.abrir();
            } catch (IOException | DocumentException ex) {
                Logger.getLogger(ImpresionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public MprManager getMpr() {
        return mpr;
    }

    public ImpresionesController(Impresiones impresiones) {
        this.impresiones = impresiones;

        this.impresiones.getBtnGrabar().addActionListener(this);
        this.impresiones.getBtnImprimir().addActionListener(this);
    }

}
