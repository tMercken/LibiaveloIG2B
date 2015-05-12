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
public class Livreur {
    private int id;
    private char typePermis;
    private String nom;
    private String prenom;
    private GregorianCalendar dateNaissance;
    private GregorianCalendar dateRecrutement;
    private String adresse;
    private Integer salaire;
    
    public Livreur(int id, char typePermis, String nom, String prenom,GregorianCalendar dateNaissance, GregorianCalendar dateRecrutement, String adresse, int salaire)
    {
        this.id = id;
        this.typePermis = typePermis;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.dateRecrutement = dateRecrutement;
        this.adresse = adresse;
        this.salaire = salaire;
        }
    
    
}
