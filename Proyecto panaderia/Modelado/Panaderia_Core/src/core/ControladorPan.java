/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import db.MySQLConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Panes;

/**
 *
 * @author bvsr9
 */
public class ControladorPan {

    private MySQLConnection conexion;

    public ControladorPan() {
        this(new MySQLConnection());
    }

    public ControladorPan(MySQLConnection conexion) {
        this.conexion = conexion;
    }

    public int nuevoPan(Panes pan) throws Exception {
        int codPan;

        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "call insertarPan( ?, ?, ?,?)";

        try {

            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);
            cstmt.setString(1, pan.getDescripcion());
            cstmt.setDouble(2, pan.getPrecioP());
            cstmt.setDouble(3, pan.getPrecioV());
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            pan.setCodPan(cstmt.getInt(4));

            cstmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            throw e;
        }
        codPan = pan.getCodPan();
        return codPan;
    }
    
    public int obtenerCodigo(int id) throws Exception{
        int numR = 0;
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;

        String query = "SELECT CodPan FROM Pan WHERE id = "+id;

        try {
            conn = conexion.abrir();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.last()) {
                numR = rs.getInt("CodPan");
            }

            rs.close();
            stmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            throw e;
        }
        return numR;
    }

    public void agregarPan(int codPan, int cantidad) throws Exception {

        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "call panExistente( ?, ?)";

        try {

            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);
            cstmt.setInt(1, codPan);
            cstmt.setInt(2, cantidad);
            cstmt.executeUpdate();

            cstmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            throw e;
        }
    }

    public String[][] existencia() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        String[][] out = null;

        String query = "SELECT * FROM grafPanes";

        int renglones = 0;
        int columnas = 2;
        int rA = 0;

        try {
            conn = conexion.abrir();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);

            if (rs.last()) {
                renglones = rs.getRow();
                out = new String[renglones][columnas];
                rs.beforeFirst();

                while (rs.next()) {
                    out[rA][0] = rs.getString("Descripcion");
                    out[rA][1] = rs.getString("Existencia");
                    rA++;
                }
            } else {
                out = new String[renglones][columnas];
            }

            rs.close();
            stmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            System.out.println(e);
            throw e;
        }

        return out;
    }

    public String[] mostrarTabla(String cod, int cantidad) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        String[] out = null;

//        String query = "SELECT * FROM panes where CodPan = '" + cod + "' ";
        String query="SELECT * FROM tablaPanes where CodPan = "+cod+" AND Existencia >= "+cantidad;

        try {
            conn = conexion.abrir();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);

            if (rs.last()) {
                rs.beforeFirst();
                out = new String[3];
                while (rs.next()) {
                    out[0] = rs.getString("CodPan");
                    out[1] = rs.getString("Descripcion");
                    out[2] = rs.getString("PrecioV");
                }
            }
//            else {
//                out = new String[3];
//            }

            rs.close();
            stmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            System.out.println(e);
            throw e;
        }

        return out;
    }

    public String[][] tablaRegistro() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        String[][] out = null;

        String query = "SELECT * FROM tablaPanes";

        int renglones = 0;
        int columnas = 5;
        int rA = 0;

        try {
            conn = conexion.abrir();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);

            if (rs.last()) {
                renglones = rs.getRow();
                out = new String[renglones][columnas];
                rs.beforeFirst();

                while (rs.next()) {
                    out[rA][0] = rs.getString("CodPan");
                    out[rA][1] = rs.getString("Descripcion");
                    out[rA][2] = rs.getString("PrecioV");
                    out[rA][3] = rs.getString("PrecioP");
                    out[rA][4] = rs.getString("Existencia");
                    rA++;
                }
            } else {
                out = new String[0][columnas];
            }

            rs.close();
            stmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            System.out.println(e);
            throw e;
        }

        return out;
    }
}
