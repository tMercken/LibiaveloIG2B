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
public class BDAccessException extends Exception {
    String e;
    
    public BDAccessException(String messageSQLError)
    {
        e = messageSQLError;
    }
    
    public String toString(){ // Vérifier orthographe
        return "Erreur lors de l'acces et/ou de la modification de la Base de donnée";    
    }
    
    public String getMessage(){ 
        return e;
    }
}