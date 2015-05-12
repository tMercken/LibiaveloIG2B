/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.GregorianCalendar;

/**
 *
 * @author Quentin
 */
public class Velo {
    
    // L'id n'est pas généré automatiquement => Integer et pas int
    private Integer id;
    private GregorianCalendar dateAchat;
    private String marque;
    private ODT ordre;
    private Station station;
    
    public Velo (int id, GregorianCalendar dateAchat, Station s) {
        this.id = id;
        this.dateAchat = dateAchat; 
        this.marque = null;
        this.ordre = null;
        this.station = s;
    }
    
    public Velo (int id, GregorianCalendar dateAchat, String marque, ODT ordre, Station station){
        this.id = id;
        this.dateAchat = dateAchat;
        this.marque = marque;
        this.ordre = ordre;
        this.station = station;
    }    
    
    public int getID(){
        return this.id;
    }
    
    public void setMarque(String m){
        this.marque = m;
    }
    
    public void setODT(ODT ordre){
        this.ordre = ordre;
    }
    
    public void setStation(Station s){
        this.station = s;
    }
    
    
}