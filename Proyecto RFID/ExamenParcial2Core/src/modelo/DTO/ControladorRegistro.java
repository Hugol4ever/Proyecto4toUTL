/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DTO;

import db.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.DAO.Participante;

/**
 *
 * @author Ozwaa
 */
public class ControladorRegistro {
    
     private MySQLConnection conexion;

    public ControladorRegistro() {
        this(new MySQLConnection());
    }

    public ControladorRegistro(MySQLConnection conexion) {
        this.conexion = conexion;
    }
    
    public Participante busquedaParticipante(int Matri) throws Exception{
        Participante part = new Participante();
        
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String query = "select * from Buscarp where Clave = ?";
        conn = conexion.abrir();
        pst = conn.prepareStatement(query);
        pst.setInt(1, Matri);
        rs = pst.executeQuery();
        while (rs.next()) {
            part.setClave(rs.getInt("Clave"));
            part.setNombre(rs.getString("Nombre"));
            part.setUtl_Procedencia(rs.getString("Utl_procedencia"));
        }
        return part;
    }
    
    public ArrayList<Participante> busquedaParticipante() throws Exception{
        ArrayList<Participante> lista = new ArrayList<>();
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        
        String query = "select * from Buscarp";
        conn = conexion.abrir();
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery(query);
        while (rs.next()) {
            Participante part = new Participante();
            part.setClave(rs.getInt("Clave"));
            part.setNombre(rs.getString("Nombre"));
            part.setUtl_Procedencia(rs.getString("Utl_procedencia"));
            lista.add(part);
        }
        return lista;
    }
    
    public ArrayList<Participante> participante2() throws Exception{
        ArrayList<Participante> lista = new ArrayList<>();
        
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        
        String query = "select * from PartiConfetres";
        conn = conexion.abrir();
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery(query);
        while (rs.next()) {
            Participante part = new Participante();
            part.setClave(rs.getInt("Clave1"));
            part.setNombre(rs.getString("Nombre"));
            lista.add(part);
        }
        return lista;
    }
    
}