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
public class Reparateur {
    private int id;
    private String diplome;
    private String nom;
    private String prenom;
    private GregorianCalendar dateNaissance;
    private GregorianCalendar dateRecrutement;
    private String adresse;
    private Integer salaire;
    
    public Reparateur(int id, String diplome, String nom, String prenom,GregorianCalendar dateNaissance, GregorianCalendar dateRecrutement, String adresse, int salaire)
    {
        this.id = id;
        this.diplome = diplome;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.dateRecrutement = dateRecrutement;
        this.adresse = adresse;
        this.salaire = salaire;
    }
    
    public int getID(){
        return this.id;
    }
}
