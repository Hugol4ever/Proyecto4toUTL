/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import db.MySQLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Panes;

/**
 *
 * @author hugol
 */
public class PanesLista {
    
   private ResultSet rs;
   private Statement st;
   private Connection cn;
   private MySQLConnection mysql = new MySQLConnection();
   
   public ArrayList<Panes> nombre() {
       ArrayList<Panes> panes = new ArrayList<Panes>();
       String query = "SELECT * FROM panes";
       try {
           cn = mysql.abrir();
           st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
           rs = st.executeQuery(query);
           while (rs.next()) {
               Panes p = new Panes();
               p.setCodPan(rs.getInt("CodPan"));
               p.setDescripcion(rs.getString("Descripcion"));
               panes.add(p);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return panes;
   }
    
}
