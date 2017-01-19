/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DTO;

import db.MySQLConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import modelo.DAO.Participante;

/**
 *
 * @author Ozwaa
 */
public class ControlUsuario {
    
    private MySQLConnection conexion;

    public ControlUsuario() {
        this(new MySQLConnection());
    }

    public ControlUsuario(MySQLConnection conexion) {
        this.conexion = conexion;
    }

    public int nuevoUsuario(Participante part) throws Exception {
        int claveU;

        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "call insertarParticipante( ?, ?, ?, ?)";

        try {

            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);
            cstmt.setString(1, part.getNombre());
            cstmt.setString(2, part.getEmail());
            cstmt.setString(3, part.getUtl_Procedencia());
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            
            cstmt.executeUpdate();
            part.setClave(cstmt.getInt(4));

            cstmt.close();
            conexion.cerrar();

        } catch (Exception e) {
            conexion.cerrar();
            throw e;
        }
        claveU = part.getClave();
        return claveU;
    }
    
}
