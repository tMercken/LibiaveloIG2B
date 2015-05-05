/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
import Data.*;
import Modele.*;
import java.util.ArrayList;
import Exception.*;

/**
 *
 * @author Quentin
 */
public class ODRManager {
    
    private ODRDBAccess bda;
    
    public ODRManager()
    {
        bda = new ODRDBAccess();
    }
    
    public void addODR(ODR ordre) throws AddODRException
    {
        //traitement, rien ici je pense
        bda.addODR(ordre);
    }
    
    public ArrayList<ODR>getAllODR() /*throws AllODRException*/
    {
        ArrayList<ODR> ODRList = bda.getAllODR();
        //traitement (inutile ici)
        return ODRList;
    }
}
