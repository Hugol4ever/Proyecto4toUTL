/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

/**
 *
 * @author Juan Rocha
 */
public class Participante {
    int clave;
    String nombre;
    String utl_Procedencia;
    String email;
    
    public Participante(){
        
    }

    public Participante(String Nombre, String utl_Procedencia, String email) {
        this.nombre = Nombre;
        this.utl_Procedencia = utl_Procedencia;
        this.email = email;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getUtl_Procedencia() {
        return utl_Procedencia;
    }

    public void setUtl_Procedencia(String utl_Procedencia) {
        this.utl_Procedencia = utl_Procedencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
