/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DTO;

import db.MySQLConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import modelo.DAO.Registro;

/**
 *
 * @author Ozwaa
 */
public class Control_Conferencia {
    
    private MySQLConnection conexion;

    public Control_Conferencia() {
        this(new MySQLConnection());
     }

     public Control_Conferencia(MySQLConnection conexion) {
        this.conexion = conexion;
     }

     public void InsertarRegistro(Registro r ) throws Exception {
       
        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "call insertarRegistro(?, ? , ?)";

        try {

             conn = conexion.abrir();
             cstmt = conn.prepareCall(query);
             cstmt.setInt(1, r.getClave_Participante().getClave());
             cstmt.setInt(2, r.getId_Conferencia().getId_Conferencia());
             cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
             cstmt.executeUpdate();
             cstmt.close();
             conexion.cerrar();

             } catch (Exception e) {
              conexion.cerrar();
             throw e;
        }
    
     }
}
