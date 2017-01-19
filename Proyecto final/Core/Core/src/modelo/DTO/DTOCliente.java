
package modelo.DTO;

import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.DAO.Cliente;
import modelo.ConnectionMysql;

/**
 *
 * @author hugol
 */
public class DTOCliente {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private ConnectionMysql conexion;
    private ResultSet rs;
    private Statement st;
    private Connection cn;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos generales">
    public boolean registrarCliente(Cliente cliente) throws Exception {
        boolean si = true;
        Connection conn = null;
        CallableStatement cstmt = null;
        
        String query = "call regCliente( ?, ?, ?, ?,"
                + "?,?,?,?,"
                + "?,?,?,?,?)";
        try {
            ByteArrayInputStream datosHuella = new ByteArrayInputStream(cliente.getIdUsuario().getIdHuella().getTemplate().serialize());
            Integer tamanioHuella = cliente.getIdUsuario().getIdHuella().getTemplate().serialize().length;
            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setBinaryStream(2, datosHuella, tamanioHuella);
            cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cstmt.setString(4, " ");
            cstmt.setString(5, " ");
            cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
            cstmt.setString(8, cliente.getNombre());
            cstmt.setString(9, cliente.getCorreo());
            cstmt.setString(10, cliente.getTelefono());
            cstmt.setString(11, cliente.getGenero());
            cstmt.setString(12, cliente.getnTarjeta());
            cstmt.registerOutParameter(13, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
        } catch (Exception e) {
            conexion.cerrar();
            si = false;
            throw e;
        } finally {
            conexion.cerrar();
        }
        return si;
    }
    
    public ArrayList<Cliente> listaCliente() {
        ArrayList<Cliente> cli = new ArrayList<>();
        String query = "SELECT * FROM vistaCliente";
        try {
           cn = conexion.abrir();
           st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
           rs = st.executeQuery(query);
           while (rs.next()) {
               Cliente c = new Cliente();
               c.setIdCliente(rs.getInt("Id_Cliente"));
               c.setNombre(rs.getString("Nombre"));
               c.setCorreo(rs.getString("Correo"));
               c.setTelefono(rs.getString("Telefono"));
               c.setGenero(rs.getString("Genero"));
               c.setnTarjeta(rs.getString("N_Tarjeta"));
               cli.add(c);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return cli;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public DTOCliente() {
        this(new ConnectionMysql());
    }

    public DTOCliente(ConnectionMysql conexion) {
        this.conexion = conexion;
    }
    //</editor-fold>

}