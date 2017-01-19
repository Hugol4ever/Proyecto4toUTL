
package modelo.DTO;

import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import modelo.DAO.Cliente;
import modelo.ConnectionMysql;

/**
 *
 * @author hugol
 */
public class DTOCliente {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private ConnectionMysql conexion;
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
            cstmt.setString(9, " ");
            cstmt.setString(10, " ");
            cstmt.setString(11, cliente.getGenero());
            cstmt.setString(12, cliente.getnTarjeta());
            cstmt.registerOutParameter(13, java.sql.Types.INTEGER);
        } catch (Exception e) {
            conexion.cerrar();
            si = false;
            throw e;
        } finally {
            conexion.cerrar();
        }
        return si;
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