/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Modele.*;
import java.sql.*;
import java.util.ArrayList;
import javax.naming.*;
import javax.sql.*;
import Exception.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author client
 */
public class RechercheBDAccess implements RechercheInterface {
    Connection connexion;
    
    public RechercheBDAccess (){    
    
    }    
    
    public ArrayList<Station> getStationList () throws BDConnectionException {
        ArrayList<Station> stationList = new ArrayList <Station> ();
        
        try{
            //ouvre le pool de connexion...
            // ne vaudrait-il mieux pas l'ouvrir au lancement du programme et le refermer à la fermeture ?
            Context ctx = new InitialContext();
            DataSource source = (DataSource) ctx.lookup("jdc/BDLibiavelo");
            connexion = source.getConnection();         
            
            String requeteSQL = "select * from Station";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            ResultSet donnees = prepStat.executeQuery();
        
            while(donnees.next()){
                // creer un nouvel objet Station à ajouter dans la list
                Station newStation = new Station(donnees.getInt("id"), donnees.getString("nomRue"), donnees.getString("numRue"), donnees.getInt("codePostal"), donnees.getString("localite"), donnees.getInt("nbrVeloMax"));
                stationList.add(newStation);
            }      
            connexion.close( );
        }
        catch(Exception e){
            throw new BDConnectionException(e.getMessage());
        }
        return stationList;

    }
    
    public ArrayList<Velo> Recherche1 () throws BDConnectionException{
        ArrayList<Velo> veloList = new ArrayList <Velo>();
        
        try{
            //ouvre le pool de connexion            
            Context ctx = new InitialContext();
            DataSource source = (DataSource) ctx.lookup("jdc/BDLibiavelo");
            connexion = source.getConnection();         
            
            //recherche avec jointure
            String requeteSQL = "select * from Station";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            ResultSet donnees = prepStat.executeQuery();
        
            while(donnees.next()){
                
                //rechercher une station à partire de l'idStation du vélo et le créer pour l'ajouter à l'objet velo
                String requeteSQLStatVel = "select * from Station where id ='"+donnees.getInt("codePostal")+"'";
                PreparedStatement prepStat2 = connexion.prepareStatement(requeteSQLStatVel);
                ResultSet donneesStatVel = prepStat2.executeQuery();
                Station StationVelo = new Station(donneesStatVel.getInt("id"), donneesStatVel.getString("nomRue"), donneesStatVel.getString("numRue"), donneesStatVel.getInt("codePostal"), donneesStatVel.getString("localite"), donneesStatVel.getInt("nbrVeloMax"));
                
                //recherche l'ordre de transport correspondant au velo et le creer
                // à modifier quand quentin postera sa partie
                OrdreDeTransport OrdreTransportVelo = new OrdreDeTransport();
                
                //finallement, on crée l'objet velo et on l'ajoute à l'ArrayList
                Velo newVelo = new Velo(donnees.getInt("id"), donnees.getDate("dateAchat"), donnees.getString("marque"), StationVelo, OrdreTransportVelo);
                veloList.add(newVelo);
            }  
            connexion.close();
        }
        catch(Exception e){
            throw new BDConnectionException(e.getMessage());
        }
        return veloList;
    }
}
