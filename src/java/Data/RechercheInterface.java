
package Data;

import java.sql.*;
import java.util.ArrayList;
import Exception.*;
import Modele.*;

public interface RechercheInterface {    
    
    public ArrayList<Station> getStationList () throws BDConnectionException;
    
    public ArrayList<Velo> Recherche1 () throws BDConnectionException;
    
    
}
