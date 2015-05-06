/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Date;

/**
 *
 * @author client
 */
public class Velo {
    private Integer idVelo;
    private Date dateAchat; // est-ce le bon format de date ? à vérifier ;)
    private String marque;
    private Station idStation;
    private OrdreDeTransport idOrdreTransport;
    
    public Velo (Integer id, Date dAchat, String m, Station idSt, OrdreDeTransport idOrdTransp){
        this.idVelo = id;
        this.dateAchat = dAchat;
        this.marque = m;
        this.idStation = idSt;
        this.idOrdreTransport = idOrdTransp;        
    }
    
    public Integer getIdVelo(){
        return this.idVelo;
    }
}
