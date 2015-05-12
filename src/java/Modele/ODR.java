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
public class ODR {
    private int id;
    private GregorianCalendar dateReparation;
    private String ordreDeTravail;
    private String remarque;
    private String couleurAppliquee;
    private Boolean declasse;
    private Integer prixReparation;
    private Velo velo;
    private Reparateur rep;
    
    public ODR(int id, GregorianCalendar dateReparation, String ordreDeTravail, boolean declasse, int prixReparation, Velo velo, Reparateur rep){
        this.id = id;
        this.dateReparation = dateReparation;
        this.ordreDeTravail = ordreDeTravail;
        this.declasse = declasse;
        this.prixReparation = prixReparation;
        this.velo = velo;
        this.rep = rep;
    }
    
    public ODR(int id, GregorianCalendar dateReparation, String ordreDeTravail, String remarque, String couleurAppliquee, boolean declasse, int prixReparation, Velo velo, Reparateur rep){
        this.id = id;
        this.dateReparation = dateReparation;
        this.ordreDeTravail = ordreDeTravail;
        this.remarque = remarque;
        this.couleurAppliquee = couleurAppliquee;
        this.declasse = declasse;
        this.prixReparation = prixReparation;
        this.velo = velo;
        this.rep = rep;
    }
    
    public int getID(){
        return this.id;
    }
    
    public GregorianCalendar getDate(){
        return this.dateReparation;
    }
    
    public String getOrdreDeTravail(){
        return this.ordreDeTravail;
    }
    
    public String getRemarque(){
        return this.remarque;
    }
    
    public String getCouleurAppliquee(){
        return this.couleurAppliquee;
    }
    
    public Boolean getDeclasse(){
        return this.declasse;
    }
    
    public Integer getPrixReparation(){
        return this.prixReparation;
    }
    
    public Velo getVelo(){
        return this.velo;
    }
    
    public Reparateur getReparateur(){
        return this.rep;
    }
    
}
