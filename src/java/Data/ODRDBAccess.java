/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;
import Modele.*;
import java.sql.*;
import java.util.ArrayList;
import Exception.*;

/**
 *
 * @author Quentin
 */
public class ODRDBAccess implements ODRInterfaceDABD{
    
    public void addODR (ODR ordre) throws AddODRException
    {
      
    }
    
        public ArrayList <ODR>getAllODR() /*throws AllODRException*/
    {
        //code SQL
        //select * from ODR
        ArrayList<ODR>allODR = new ArrayList<ODR>();
        //boucler sur toutes les lignes de la table ODR selectionnée
        //créer les objets ODR et ajouter à la liste
        return allODR;
    }
    
}
