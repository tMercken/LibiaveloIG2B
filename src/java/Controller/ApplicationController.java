/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Business.*;
import Modele.*;
import java.util.ArrayList;
import Exception.*;

/**
 *
 * @author Quentin
 */
public class ApplicationController {
    private ODRManager om;
    
    public ApplicationController()
    {
        om = new ODRManager();
    }
    
    public void addODR(ODR ordre) throws AddODRException
    {
        om.addODR(ordre);
    }
    
    public ArrayList<ODR> getAllODR() /*throws AllODRException*/
    {
        return om.getAllODR();
    }
    
}
