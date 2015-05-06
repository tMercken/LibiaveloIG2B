/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author client
 */
 public class BDConnectionException extends Exception {
    String e;
    
    public BDConnectionException(String messageSQLError)
    {
        e = messageSQLError;
    }
    
    public String toString(){
    return e;
    }
    
    public String getMessage(){ // Vérifier orthographe
        return "Erreur lors de la tentative de connexion à la Base de donnée";
    }
}

