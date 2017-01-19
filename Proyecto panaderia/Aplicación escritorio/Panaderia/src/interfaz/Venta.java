/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import core.ControladorPan;
import core.ControladorVentas;
import iText.PDFnota;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Detalle;
import model.Panes;
import model.Ventas;

/**
 *
 * @author bvsr9
 */
public class Venta extends javax.swing.JPanel {

    ControladorPan cP;
    ControladorVentas cV;
    DefaultTableModel miTabla;

    int num;

    /**
     * Creates new form Venta
     */
    public Venta() {
        initComponents();

        configurarTabla();
        cP = new ControladorPan();
        cV = new ControladorVentas();
    }

    private void configurarTabla() {
        miTabla = new DefaultTableModel();
        miTabla.addColumn("Código");
        miTabla.addColumn("Nombre de Pan");
        miTabla.addColumn("Precio");
        miTabla.addColumn("Cantidad");
        miTabla.addColumn("Total");
        this.jTable1.setModel(miTabla);
    }

    private void limpiar() {
        configurarTabla();
        jTextField1.setText("");
        jTextField2.setText("");
        txtTotal.setText("");
        jTextField1.requestFocus();
        num = 0;
    }

    private void addPan(String cod, int cantidad) {
        try {
            String[] panesito = cP.mostrarTabla(cod, cantidad);
            if (panesito != null) {
                double total = Double.valueOf(panesito[2]) * cantidad;
                Object[] xx = {panesito[0], panesito[1], panesito[2], cantidad, total};
                miTabla = (DefaultTableModel) this.jTable1.getModel();
                miTabla.addRow(xx);
            } else {
                JOptionPane.showMessageDialog(null, "Codigo de pan no existente o cantidad mayor a la existente", "Verificar", JOptionPane.ERROR_MESSAGE);
                jTextField1.requestFocus();
            }
        } catch (Exception ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void generarNota() throws DocumentException, BadElementException, IOException {
        String datos[][];
        datos = new String[jTable1.getRowCount()][4];
        for (int i = 0; i < datos.length; i++) {
            datos[i][0] = String.valueOf(jTable1.getValueAt(i, 1));
            datos[i][1] = String.valueOf(jTable1.getValueAt(i, 2));
            datos[i][2] = String.valueOf(jTable1.getValueAt(i, 3));
            datos[i][3] = String.valueOf(jTable1.getValueAt(i, 4));
        }

        PDFnota nota = new PDFnota();
        nota.setDatos(datos);
        nota.setTotal(Double.valueOf(txtTotal.getText()));

        String titulo = "Factura";
        String autor = "Panaderia Foxina";
        String asunto = "Crear PDF";
        String version = "1";
        String contenido = "Nota N°" + num;

        nota.crearPDF(titulo, autor, asunto, version, contenido);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/madera.jpg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(167, 21, 57));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MV Boli", 0, 18), new java.awt.Color(167, 21, 57))); // NOI18N

        jLabel2.setFont(new java.awt.Font("MV Boli", 1, 17)); // NOI18N
        jLabel2.setText("Código:");

        jLabel3.setFont(new java.awt.Font("MV Boli", 1, 17)); // NOI18N
        jLabel3.setText("Cantidad:");

        jTextField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2.setText("1");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("MV Boli", 0, 15)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorderPainted(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnAgregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("MV Boli", 1, 17)); // NOI18N
        jLabel4.setText("Total:");

        txtTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTotal.setText("0.0");
        txtTotal.setEnabled(false);

        jButton2.setFont(new java.awt.Font("MV Boli", 0, 15)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/venta2.png"))); // NOI18N
        jButton2.setText("Realizar Venta");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (jTextField1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta código", "Verificar", JOptionPane.WARNING_MESSAGE);
            jTextField1.requestFocus();
        } else if (jTextField2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta cantidad", "Verificar", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
        } else {
            nP();
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        double total = 0.0;
        if (jTable1.getRowCount() > 0) {
            total = Double.parseDouble(txtTotal.getText());
            insertarVenta(total);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha agreado pan", "Verificar", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void insertarVenta(double total) {
        Ventas venta = new Ventas();
        try {
            venta.setFolio(cV.Venta(total));
            num = venta.getFolio();
            Detalle detalle;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                detalle = new Detalle();
                detalle.setFolio(venta);
                detalle.setPanes(new Panes());
                detalle.setCantidad(Integer.parseInt(String.valueOf(jTable1.getValueAt(i, 3))));
                detalle.getPanes().setCodPan(Integer.parseInt(String.valueOf(jTable1.getValueAt(i, 0))));
                detalle.setImporte(Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 2))));

                cV.Detalle(detalle);
            }
            JOptionPane.showMessageDialog(this, "Registro agregado", "Moviento realizado con exito", JOptionPane.INFORMATION_MESSAGE);
          
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Fallo conexión con BD", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            generarNota();
            limpiar();
        } catch (DocumentException ex) {
            System.out.println(ex);
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        char x = evt.getKeyChar();
        if (jTextField1.getText().length() >= 8) {
            evt.consume();
        } else if ((x >= '0' && x <= '9')) {

        } else {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char x = evt.getKeyChar();
        if (x >= '0' && x <= '9') {

        } else {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            String cod = this.jTextField1.getText();
            nP();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void nP() {
        String cod = jTextField1.getText();
        int cantidad = Integer.parseInt(jTextField2.getText());
        if (cantidad < 1) {
            JOptionPane.showMessageDialog(null, "Cantidad no valida", "Verificar", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
        } else {
            addPan(cod, cantidad);
            total();
        }
    }

    private void total() {
        double total = 0.0;
        int tF = jTable1.getRowCount();
        for (int i = 0; i < tF; i++) {
            total += Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 4)));
        }
        txtTotal.setText(String.valueOf(total));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
