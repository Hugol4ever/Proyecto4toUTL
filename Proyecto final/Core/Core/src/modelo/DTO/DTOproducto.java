/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.ConnectionMysql;
import modelo.DAO.Producto;
/**
 *
 * @author Juan Rocha
 */
public class DTOproducto {
   private ResultSet rs;
   private Statement st;
   private Connection cn;
   private ConnectionMysql mysql = new ConnectionMysql();
   
   public ArrayList<Producto> ListaProducto(String parametro, String valor) {
       ArrayList<Producto> prod = new ArrayList<Producto>();
       String query = "SELECT * FROM vistProductos where " + parametro + " = '" + valor + "'";
       try {
           cn = mysql.abrir();
           st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
           rs = st.executeQuery(query);
           while (rs.next()) {
               Producto p= new Producto();
               p.setIdProducto(rs.getInt("Id_Producto"));
               p.setNombre(rs.getString("Nombre"));
               p.setMarca(rs.getString("Marca"));
               p.setCategoria(rs.getString("Categoria"));
               p.setPrecio(rs.getDouble("Precio"));
               p.setFoto(rs.getString("Foto"));
               prod.add(p);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return prod;
   }
   
   public ArrayList<Producto> ListaProducto() {
       ArrayList<Producto> prod = new ArrayList<Producto>();
       String query = "SELECT * FROM vistProductos";
       try {
           cn = mysql.abrir();
           st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
           rs = st.executeQuery(query);
           while (rs.next()) {
               Producto p= new Producto();
               p.setIdProducto(rs.getInt("Id_Producto"));
               p.setNombre(rs.getString("Nombre"));
               p.setMarca(rs.getString("Marca"));
               p.setCategoria(rs.getString("Categoria"));
               p.setPrecio(rs.getDouble("Precio"));
               p.setFoto(rs.getString("Foto"));
               prod.add(p);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return prod;
   }
   
   public String[] ListaProducto2(int id) {
       String[] prod = new String[3];
       String query = "SELECT Id_Producto, Nombre, Precio FROM vistProductos where Id_Producto = " + id;
       try {
           cn = mysql.abrir();
           st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
           rs = st.executeQuery(query);
           while (rs.next()) {
               prod[0] = String.valueOf(rs.getInt("Id_Producto"));
               prod[1] = rs.getString("Nombre");
               prod[2] = String.valueOf(rs.getDouble("Precio"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return prod;
   }
   
}