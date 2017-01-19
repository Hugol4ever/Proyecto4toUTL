package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.Conferencia;
import modelo.DAO.Participante;
import modelo.DAO.Registro;
import modelo.DTO.Control_Conferencia;
import modelo.DTO.ControladorRegistro;
import mprlib.MprCommands;
import mprlib.MprManager;
import vistas.RegistroParticipantes;

/**
 *
 * @author hugol
 */
public class RegistroParticipantesController implements ActionListener {

    private RegistroParticipantes registroParticipantes;
    private MprManager mpr;
    private Lectura lectura;

    public void iniciarVista() {
        try {
            registros();
        } catch (Exception ex) {
            Logger.getLogger(RegistroParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registros() throws Exception {
        String puerto = "COM6";
        mpr = new MprManager(puerto, 9600);
        mpr.setByteSeparator("-");
        if (!mpr.connect()) {
            throw new Exception("No hay Comunicacion camarita");
        }
        lectura = new Lectura(mpr, this.registroParticipantes.getTblParticipantes());
        lectura.start();
    }

    public RegistroParticipantesController(RegistroParticipantes registroParticipantes) {
        this.registroParticipantes = registroParticipantes;

        this.registroParticipantes.getBtnDeneter().addActionListener(this);
        this.registroParticipantes.getBtnRegistrar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.registroParticipantes.getBtnDeneter() == e.getSource()) {
            lectura.stop();
            this.mpr.close();
            registrar();
        } else {

        }
    }

    public MprManager getMpr() {
        return mpr;
    }

    private void registrar() {
        String conf = this.registroParticipantes.getCmbxConferencia().getSelectedItem().toString();
        int confe;
        int rows = this.registroParticipantes.getTblParticipantes().getRowCount();
        if (conf.equals("Conferencia1")) {
            confe = 001;
        } else {
            confe = 002;
        }
        for (int i = 0; i < rows; i++) {
            try {
                Registro r = new Registro();
                r.setClave_Participante(new Participante());
                r.getClave_Participante().setClave(Integer.parseInt(this.registroParticipantes.getTblParticipantes().getValueAt(i, 0).toString()));
                r.setId_Conferencia(new Conferencia());
                r.getId_Conferencia().setId_Conferencia(confe);
                Control_Conferencia cf = new Control_Conferencia();
                cf.InsertarRegistro(r);
            } catch (Exception ex) {
                Logger.getLogger(RegistroParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Registrado en la conferencia " + confe + " con éxito");
    }

}

class Lectura extends Thread {

    MprManager fox;
    String datoByte;
    String datoAscii;
    ArrayList<String> lista = new ArrayList<>();
    JTable tabla;

    public Lectura(MprManager mpr, JTable tabla) {
        fox = mpr;
        datoByte = datoAscii = null;
        this.tabla = tabla;
    }

    @Override
    public void run() {
        configurarTabla();
        try {
            while (true) {
                leer();
            }
        } catch (Exception e) {
            System.out.println("Error al leer tag");
        }
    }

    private void leer() {
        try {
            datoByte = fox.readOneTagID(MprCommands.TagProtocol.EPC_C1G2, 1);
            datoAscii = convertirBytes(datoByte);
            datoAscii = datoAscii.trim();
            System.out.println("Dato en Bytes: " + datoByte);
            System.out.println("Dato leido: " + datoAscii);
            agregar();
        } catch (Exception e) {
        }
    }

    public String xxx() {
        return datoAscii;
    }

    private void agregar() {
        boolean bandera = false;
        if (!lista.isEmpty()) {
            for (String lista1 : lista) {
                if (datoAscii.equals(lista1)) {
                    bandera = true;
                    break;
                }
            }
            if (!bandera) {
                lista.add(datoAscii);
                agregarTabla(Integer.parseInt(datoAscii));
            }
        } else {
            lista.add(datoAscii);
            agregarTabla(Integer.parseInt(datoAscii));
        }
    }

    public void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Matricula");
        modelo.addColumn("Nombre");
        modelo.addColumn("UTL - procedencia");
        this.tabla.setModel(modelo);
    }

    private void agregarTabla(int id) {
        DefaultTableModel modelo = (DefaultTableModel) this.tabla.getModel();
        if (id != 0) {
            modelo.addRow(registro(id));
            this.tabla.setModel(modelo);
        }
    }

    private String[] registro(int matricula) {
        ControladorRegistro cr = new ControladorRegistro();
        Participante p = new Participante();
        try {
            p = cr.busquedaParticipante(matricula);
            System.out.println("si");
        } catch (Exception ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] reg = {String.valueOf(p.getClave()), p.getNombre(), p.getUtl_Procedencia()};
        return reg;
    }

    public String convertirBytes(String datoEnBytes) {
        String salida = "";
        String[] datos = datoEnBytes.split("-");
        try {
            for (String s : datos) {
                salida += (char) Integer.parseInt(s.trim(), 16);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return salida;
    }
}
