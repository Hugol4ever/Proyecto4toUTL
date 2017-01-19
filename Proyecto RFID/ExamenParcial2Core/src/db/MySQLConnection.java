
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hugo_
 */
public class MySQLConnection {
     private String driverName;
    private String url;
    private String servidor;
    private int puerto;
    private String baseDeDatos;
    private String userName;
    private String password;

    Connection connection;
    
     public MySQLConnection() {
        this ("com.mysql.jdbc.Driver", "localhost", 3306, "RFID", "root", "root");
    }
    
    public MySQLConnection(String driverName, String servidor, int puerto, String baseDeDatos, String userName, String password) {
        this.driverName = driverName;
        this.servidor = servidor;
        this.puerto = puerto;
        this.baseDeDatos = baseDeDatos;
        this.userName = userName;
        this.password = password;
        
        try {
            Class.forName(driverName);
            url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + baseDeDatos;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection abrir() throws Exception {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }
    
     public void cerrar() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
}
