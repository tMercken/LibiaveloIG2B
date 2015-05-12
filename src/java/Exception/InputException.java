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
public class InputException extends Exception{
    String e;
    
    public InputException(String messageError)
    {
        e = messageError;
    }
    
    public String toString(){ // Vérifier orthographe
        return "Les données entrées par l'utilisateur sont erronées";    
    }
    
    public String getMessage(){ 
        return e;
    }
}