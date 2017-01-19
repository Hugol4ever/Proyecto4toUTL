
package modelo.DTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import modelo.ConnectionMysql;
import modelo.DAO.DetalleVenta;

/**
 *
 * @author bvsr9
 */
public class DTODetalleVenta {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private ConnectionMysql conexion;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos generales">
    public int insert(DetalleVenta detalle) throws Exception {
        int id;
        Connection conn = null;
        CallableStatement cstmt = null;

        String query = "CALL regDetVenta( ?, ?, ?, ?, ?)";

        try {
            conn = conexion.abrir();
            cstmt = conn.prepareCall(query);

            cstmt.setInt(1, detalle.getCantidad());
            cstmt.setDouble(2, detalle.getPrecio());
            cstmt.setInt(3, detalle.getIdProducto().getIdProducto());
            cstmt.setInt(4, detalle.getIdVenta().getIdVenta());
            cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
            cstmt.executeUpdate();

            detalle.setIdDetalleVenta(cstmt.getInt(5));
            id = detalle.getIdDetalleVenta();

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
    public DTODetalleVenta() {
        this(new ConnectionMysql());
    }

    public DTODetalleVenta(ConnectionMysql conexion) {
        this.conexion = conexion;
    }
    //</editor-fold>
}
