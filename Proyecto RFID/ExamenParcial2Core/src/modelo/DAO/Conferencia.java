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
public class Conferencia {
    int id_Conferencia;
    String titulo;
    String Conferencista;
    
    public Conferencia (){
        
    }

    public Conferencia(String titulo, String Conferencista) {
        this.titulo = titulo;
        this.Conferencista = Conferencista;
    }

    public int getId_Conferencia() {
        return id_Conferencia;
    }

    public void setId_Conferencia(int id_Conferencia) {
        this.id_Conferencia = id_Conferencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConferencista() {
        return Conferencista;
    }

    public void setConferencista(String Conferencista) {
        this.Conferencista = Conferencista;
    }
    
}
