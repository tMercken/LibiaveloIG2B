/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exception.BDConnectionException;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author client
 */
public class SingletonConnexion {
    
    private static Connection connexionUnique;
            
    private SingletonConnexion(){
        
    }
    
    public static Connection getConnexionUnique() throws BDConnectionException {
        if(connexionUnique == null){
            //
            try {
            Context ctx = new InitialContext();
            DataSource source = (DataSource) ctx.lookup("jdc/BDLibiavelo");
            connexionUnique = source.getConnection();
        }
        catch(Exception e) {
            throw new BDConnectionException(e.getMessage()); 
        }
        }
        return connexionUnique;
    }
       
}
