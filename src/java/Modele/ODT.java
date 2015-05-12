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
public class ODT {
    private int id;
    private boolean exceptionnel;
    private BonDeLivraison bon;
    private Station station;
    private Station stationReceptrice;
    
    public ODT(int id, boolean exceptionnel, BonDeLivraison bon, Station station, Station stationReceptrice)
    {
        this.id = id;
        this.exceptionnel = exceptionnel;
        this.bon = bon;
        this.station = station;
        this.stationReceptrice = stationReceptrice;
    }
    
}
