/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DTO;

import db.MySQLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.DAO.Participante;

/**
 *
 * @author Ozwaa
 */
public class ControlCertificado {

    private MySQLConnection conexion;

    public ControlCertificado() {
        this(new MySQLConnection());
    }

    public ControlCertificado(MySQLConnection conexion) {
        this.conexion = conexion;
    }

    public Participante[] busquedaRegistro(int Matri) throws Exception {
        Participante[] part;
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;

        String query = "select * from PartiConfetres";
        conn = conexion.abrir();
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery(query);
        int rA = 0;
        if (rs.last()) {
            part = new Participante[rs.getRow()];
            rs.beforeFirst();
            while (rs.next()) {
                part[rA] = new Participante();
                part[rA].setClave(rs.getInt("Clave1"));
                part[rA].setNombre(rs.getString("Nombre"));
                rA++;
            }
        } else {
            part = new Participante[0];
        }
        return part;
    }

}
