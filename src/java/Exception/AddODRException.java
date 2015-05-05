/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author Quentin
 */
public class AddODRException extends Exception {
    String e;
    
    public AddODRException(String ordre)
    {
        e = ordre;
    }
    
    public String toString(){
    return "L'ordre de réparation " + e + " n'est pas valide";}
}

