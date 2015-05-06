/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author client
 */
public class Station {
    private Integer id; //pas vraiment besoin de mettre Integer plutôt que int
    private String nomRue;
    private String numRue;
    private Integer codePostal; // vu que ces variables ne sont normalement jamais null
    private String localite;
    private Integer nbrVeloMax; // Mais mieux vaut prévenir que guérir...
    
    public Station(Integer id, String nomR, String numR, Integer codeP,String local, Integer nbVeloM){
        this.id = id;
        this.nomRue = nomR;
        this.numRue = numR;
        this.codePostal = codeP;
        this.localite = local;
        this.nbrVeloMax = nbVeloM;
    }
    
    public Integer getIdStation(){
        return this.id;
    }
    
    public Integer getNbVeloMax(){
        return this.nbrVeloMax;
    }
}
