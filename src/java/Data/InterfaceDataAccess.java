
package Data;

import java.sql.*;
import java.util.ArrayList;
import Exception.*;
import Modele.*;
import java.util.GregorianCalendar;

public interface InterfaceDataAccess { 
    
    public void openConnection()throws BDConnectionException;
    
    public void closeConnection()throws BDExctinctionException;
    
    public void commitChange() throws BDAccessException;
    
    public void undoChange() throws BDAccessException;
    
    public ArrayList<ODR> getOrdreDeReparationList () throws BDAccessException;
    
    public int insertionODR (ODR ordreDeReparation) throws BDAccessException;
    
    public int modificationODR (ODR ordreDeReparation) throws BDAccessException;
            
    public int suppressionODR (int idOrdreDeReparation) throws BDAccessException;    
    
    public ArrayList<Station> getStationList() throws BDAccessException;
    
    public ArrayList<Velo> getVeloList() throws BDAccessException;
    
    public ArrayList<Velo> getVeloToStation (Station stationRecherche) throws BDAccessException;
    
    public ArrayList<Reparateur> getDernierReparateurVelo (int idVelo ) throws BDAccessException;
    
    public int getNbrVeloRepare (GregorianCalendar DateDebut, GregorianCalendar Datefin, Station stationRech) throws BDAccessException, InputException;
    
    
}
