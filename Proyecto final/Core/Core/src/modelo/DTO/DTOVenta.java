/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import modelo.ConnectionMysql;
import modelo.DAO.Venta;

/**
 *
 * @author bvsr9
 */
public class DTOVenta {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private ConnectionMysql conexion;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos generales">
    public int insert(Venta venta) throws Exception {
        int id;
        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "CALL regVenta( ?, ?)";

        try {
            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);

            cstmt.setInt(1, venta.getIdCliente().getIdCliente());
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            cstmt.executeUpdate();

            venta.setIdVenta(cstmt.getInt(2));
            id = venta.getIdVenta();

            cstmt.close();
            conexion.cerrar();
        } catch (Exception e) {
            conexion.cerrar();
            throw e;
        }
        return id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public DTOVenta() {
        this(new ConnectionMysql());
    }

    public DTOVenta(ConnectionMysql conexion) {
        this.conexion = conexion;
    }
    //</editor-fold>
}
