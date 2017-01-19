/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.util.Date;

/**
 *
 * @author Juan Rocha
 */
public class Registro {
    Date fecha;
    String hora ;
    Participante clave_Participante;
    Conferencia id_Conferencia ;
    
    public Registro(){
        
    }

    public Registro(Date fecha, String hora, Participante clave_Participante, Conferencia id_Conferencia) {
        this.fecha = fecha;
        this.hora = hora;
        this.clave_Participante = clave_Participante;
        this.id_Conferencia = id_Conferencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Participante getClave_Participante() {
        return clave_Participante;
    }

    public void setClave_Participante(Participante clave_Participante) {
        this.clave_Participante = clave_Participante;
    }

    public Conferencia getId_Conferencia() {
        return id_Conferencia;
    }

    public void setId_Conferencia(Conferencia id_Conferencia) {
        this.id_Conferencia = id_Conferencia;
    }
    
}
