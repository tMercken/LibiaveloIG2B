/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Quentin
 */
public class Station {
    private int id;
    private String nomRue;
    private String numRue;
    
    // Même si codePostal est obligatoire et que l'utilisateur ne touche pas au station, ça ne devrait pas être un int selon moi è_é
    private Integer codePostal;
    private String localite;
    
    public Station(int id, String nomRue, String numRue, int codePostal, String localite)
    {
        this.id = id;
        this.nomRue = nomRue;
        this.numRue = numRue;
        this.codePostal = codePostal;
        this.localite = localite;
    }            
    
    public int getID(){
        return this.id;
    }
}
    
    