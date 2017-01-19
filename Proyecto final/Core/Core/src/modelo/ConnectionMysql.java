/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Juan Rocha
 */
public class ConnectionMysql {
    
    private String driverName;
    private String url;
    private String servidor;
    private int puerto;
    private String baseDeDatos;
    private String userName;
    private String password;

    Connection connection;
    Statement st;
    
     public ConnectionMysql () {
        this ("com.mysql.jdbc.Driver", "localhost", 3306, "Final", "root", "root");
    }
    
    public ConnectionMysql(String driverName, String servidor, int puerto, String baseDeDatos, String userName, String password) {
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

