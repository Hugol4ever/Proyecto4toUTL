/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import commons.Globals;
import db.MySQLConnection;
import model.Ventas;
import model.Detalle;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Panes;

/**
 *
 * @author Juan Rocha
 */
public class ControladorVentas {

    private MySQLConnection conexion;

    public ControladorVentas() {
        this(new MySQLConnection());
    }

    public ControladorVentas(MySQLConnection conexion) {
        this.conexion = conexion;
    }

    public int Venta(double total) throws Exception {
        int numeroVenta;
        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "call venta( ?,  ?)";

        try {

            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);
            cstmt.setDouble(1, total);
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            numeroVenta = cstmt.getInt(2);

            cstmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            throw e;
        }
        return numeroVenta;
    }

    public void Detalle(Detalle d) throws Exception {

        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "call detalle(?,?,?,?)";

        try {
            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);
            cstmt.setInt(1, d.getCantidad());
            cstmt.setDouble(2, d.getImporte());
            cstmt.setInt(3, d.getPanes().getCodPan());
            cstmt.setInt(4, d.getFolio().getFolio());
            cstmt.executeUpdate();
            cstmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            throw e;
        }
    }

}
