/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Modele.*;
import java.sql.*;
import java.util.*;
import Exception.*;
/**
 *
 * @author client
 */
public class BDAccess implements InterfaceDataAccess {
    Connection connexion;
    
    public BDAccess (){ 
        
    }
    
    public void openConnection () throws BDConnectionException{
        //ouvre le pool de connexion à l'aide d'un singloton et supprime le commit automatique
        // ne vaudrait-il mieux pas tenter de l'ouvrir au lancement de chaque fonction et le refermer à la fermeture du programme?
        try{
            connexion = SingletonConnexion.getConnexionUnique();
            connexion.setAutoCommit(false);
        }
        // Ce catch est-il propre ? Il récupère soit un SQLException soit un BDConnectionException, ne vaudrait il pas mieux faire deux catch ?
        catch(Exception e){
            throw new BDConnectionException(e.getMessage());
        }
    }
    
    public void closeConnection() throws BDExctinctionException{
        try{
            connexion.close();
        }
        catch (SQLException e){
            throw new BDExctinctionException(e.getMessage());
        }
    }
    
    public void commitChange() throws BDAccessException{
        try{
            connexion.commit();
        }
        catch (SQLException e){
            throw new BDAccessException(e.getMessage());
        }
    }
    
    public void undoChange() throws BDAccessException{
        try {
            connexion.rollback();
        }
        catch (SQLException e){
            throw new BDAccessException(e.getMessage());
        }
    }
  
    public ArrayList <ODR> getOrdreDeReparationList () throws BDAccessException { /** 
     * renvoie la liste de tous les ODR
     * Il contienne bien evidemment un velo et un reparateur, mais le velo ne contient pas d'ODT, pour alléger le code */
        
        ArrayList<ODR> odrList = new ArrayList<ODR> ();
        try{
            String requeteSQL = "select * from OrdreDeReparation;";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            ResultSet donnees = prepStat.executeQuery();
        
            while(donnees.next()){
                
                // on commence par créer la station du velo, puis le velo
                String requeteSQLVeloODR = "select * from Velo where id = "+ donnees.getInt("IDVelo")+";";
                PreparedStatement prepStat2 = connexion.prepareStatement(requeteSQLVeloODR);
                ResultSet donneesVeloODR = prepStat2.executeQuery();

                GregorianCalendar dateAchat = new GregorianCalendar();
                dateAchat.setTime(donneesVeloODR.getDate("dateAchat"));
                
                String requeteSQLStatVeloODR = "select * from Station where id ="+donnees.getInt("IDStation") +";";
                PreparedStatement prepStat3 = connexion.prepareStatement(requeteSQLStatVeloODR);
                ResultSet donneesStatVelo = prepStat3.executeQuery();
                Station stationVelo = new Station(donneesStatVelo.getInt("id"), donneesStatVelo.getString("nomRue"), donneesStatVelo.getString("numRue"), donneesStatVelo.getInt("codePostal"), donneesStatVelo.getString("localite"));
                
                Velo veloODR = new Velo (donneesVeloODR.getInt("id"), dateAchat, stationVelo);
                
                //vérifier si la marque du vélo n'est pas de type null
                String marque = donneesVeloODR.getString("marque");
                if (donneesVeloODR.wasNull( ) == false)
                    {veloODR.setMarque(marque);} 
                
                // On va maintenant créer le reparateur
                String requeteSQLReparateurODR = "select * from reparateur where id ="+donnees.getInt("IDReparateur");
                PreparedStatement prepStat4 = connexion.prepareStatement(requeteSQLReparateurODR);
                ResultSet donneesReparateurODR = prepStat4.executeQuery();
                
                GregorianCalendar dateNaissance = new GregorianCalendar();
                dateNaissance.setTime(donneesReparateurODR.getDate("dateNaissance"));
                
                GregorianCalendar dateRecrutement = new GregorianCalendar();
                dateRecrutement.setTime(donneesReparateurODR.getDate("dateRecrutement"));
                                
                Reparateur reparateurODR = new Reparateur(donneesReparateurODR.getInt("id"), donneesReparateurODR.getString("diplome"), donneesReparateurODR.getString("nom"),donneesReparateurODR.getString("prenom"), dateNaissance, dateRecrutement, donneesReparateurODR.getString("adresse"), donneesReparateurODR.getInt("salaire") );
                
                // on peut maintenant creer l'ordre de reparation en lui même
                GregorianCalendar dateReparation = new GregorianCalendar();
                dateReparation.setTime(donnees.getDate("dateReparation"));                
                
                ODR newODR  = new ODR(donnees.getInt("id"),dateReparation, donnees.getString("ordreDeTravail  "), donnees.getBoolean("declasse"), donnees.getInt("prixReparation"), veloODR, reparateurODR);
                
                odrList.add(newODR);
            }
        }
        catch(SQLException e){
            throw new BDAccessException(e.getMessage());
        }
        return odrList;
        
    }
    
    public int insertionODR (ODR ordreDeReparation) throws BDAccessException {
        int nbLigneMod = 0;
                
        try
        {
            String instructionSQL= "insert into OrdreDeReparation (dateReparation, ordreDeTravail, remarque, couleurAppliquee, declassee, prixReparation, IDVelo, IDReparateur)" +
"values (?, ?, ?, ?, ?, ?, ?,?);"; //(select id from Reparateur where nom = 'Mercken' and prenom = 'Thierry')
            PreparedStatement prepStat= connexion.prepareStatement(instructionSQL);
            
            //si la date est Null, est est mise par défaut à celle du jour.
            if(ordreDeReparation.getDate()!= null){
                prepStat.setDate(1, new java.sql.Date(ordreDeReparation.getDate().getTimeInMillis()));
            }
            else {
                prepStat.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            }
            
            prepStat.setString(2, ordreDeReparation.getOrdreDeTravail());
            
            prepStat.setString(3, ordreDeReparation.getRemarque());
            
            if(ordreDeReparation.getCouleurAppliquee() != null){
                prepStat.setString(4, ordreDeReparation.getCouleurAppliquee());
            }
            else {
                prepStat.setNull(4, Types.VARCHAR);
            }
            
            //si declasse n'est pas précisé, mis à false par défaut
            if(ordreDeReparation.getDeclasse() != null){
                prepStat.setBoolean(5, ordreDeReparation.getDeclasse());
            }
            else {
                prepStat.setBoolean(5, false);
            }
            
            prepStat.setInt(6, ordreDeReparation.getPrixReparation());
            
            prepStat.setInt(7, ordreDeReparation.getVelo().getID());
            
            prepStat.setInt(8,ordreDeReparation.getReparateur().getID());
            
            nbLigneMod = prepStat.executeUpdate();
        }
        catch(Exception e){
            throw new BDAccessException(e.getMessage());
        }
        return nbLigneMod;
    }
    
    public int modificationODR (ODR ordreDeReparation) throws BDAccessException {
        int nbLigneMod = 0;
        try{
            //note: pour l'instant: modifie remplace toutes les valeurs de la table par celles de l'objet. Très simple mais nbLigneMod n'est donc pas à jour.
            String requeteSQL = "update OrdreDeReparation set datereparation = ?, ordreDeTravail = ?, remarque = ?, couleurAppliquee = ?, declasse = ?, prixReparation = ?, IDVelo = ?, IDReparateur = ? where id = ?;";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            
            //si date non précisée, mise à today par défaut 
            if(ordreDeReparation.getDate()!= null){
                prepStat.setDate(1, new java.sql.Date(ordreDeReparation.getDate().getTimeInMillis()));
            }
            else {
                prepStat.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            }
            
            prepStat.setString(2, ordreDeReparation.getOrdreDeTravail());
            
            prepStat.setString(3, ordreDeReparation.getRemarque());
            
            if(ordreDeReparation.getCouleurAppliquee() != null){
                prepStat.setString(4, ordreDeReparation.getCouleurAppliquee());
            }
            else {
                prepStat.setNull(4, Types.VARCHAR);
            }
            
            //si declasse n'est pas précisé, mis à false par défaut
            if(ordreDeReparation.getDeclasse() != null){
                prepStat.setBoolean(5, ordreDeReparation.getDeclasse());
            }
            else {
                prepStat.setBoolean(5, false);
            }
            
            prepStat.setInt(6, ordreDeReparation.getPrixReparation());
            
            prepStat.setInt(7, ordreDeReparation.getVelo().getID());
            
            prepStat.setInt(8,ordreDeReparation.getReparateur().getID());           
            
            nbLigneMod = prepStat.executeUpdate();            
            
        }
        catch(SQLException e){
            throw new BDAccessException(e.getMessage());
        }
        return nbLigneMod;
    }
    
    public int suppressionODR (int idOrdreDeReparation) throws BDAccessException{ 
        int nbLigneMod = 0;
        
        try{
            //note: pour l'instant: modifie remplace toutes les valeurs de la table par celles de l'objet. Très simple mais nbLigneMod n'est donc pas à jour.
            String requeteSQL = "delete  from OrdreDeReparation; where id = " + idOrdreDeReparation + ";";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            nbLigneMod = prepStat.executeUpdate();
        }
        catch(SQLException e){
            throw new BDAccessException(e.getMessage());
        }
        
        
        return nbLigneMod;
    }
    
    public ArrayList<Station> getStationList () throws BDAccessException { /**
     * La liste envoyée par cette fonction vise à remplire la jcombobox qui permettera de choisire la station dans la recherche 1 et 3*/
        
        ArrayList<Station> stationList = new ArrayList <Station> ();
        
        try{
            String requeteSQL = "select * from Station";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            ResultSet donnees = prepStat.executeQuery();
        
            while(donnees.next()){
                // creer un nouvel objet Station à ajouter dans la list
                Station newStation = new Station(donnees.getInt("id"), donnees.getString("nomRue"), donnees.getString("numRue"), donnees.getInt("codePostal"), donnees.getString("localite"));
                stationList.add(newStation);
            }              
        }
        
        catch(SQLException e){
            throw new BDAccessException(e.getMessage());
        }
        return stationList;
    }
    
    public ArrayList<Velo> getVeloList () throws BDAccessException {/**
     * La liste envoyée par cette fonction vise à remplire la jcombobox qui permettera de choisire le velo dans la recherche 2*/
        
        ArrayList<Velo> veloList = new ArrayList <Velo> ();
        
        try{
            String requeteSQL = "select * from Velo";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            ResultSet donnees = prepStat.executeQuery();
        
            while(donnees.next()){ // creer un nouvel objet Velo à ajouter dans la list
                
                GregorianCalendar dateAchat = new GregorianCalendar();
                dateAchat.setTime(donnees.getDate("dateAchat"));
                
                //creer un objet station, qui correspond à la station d'origine du vélo
                String requeteSQLStatVelo = "select * from Station where id ="+donnees.getInt("IDStation") +";";
                PreparedStatement prepStat2 = connexion.prepareStatement(requeteSQLStatVelo);
                ResultSet donneesStatVelo = prepStat2.executeQuery();
                Station stationVelo = new Station(donneesStatVelo.getInt("id"), donneesStatVelo.getString("nomRue"), donneesStatVelo.getString("numRue"), donneesStatVelo.getInt("codePostal"), donneesStatVelo.getString("localite"));
                
                Velo newVelo = new Velo (donnees.getInt("id"), dateAchat, stationVelo);
                
                //vérifier si certaines variable du vélo sont de type null
                String marque = donnees.getString("marque");
                if (donnees.wasNull( ) == false)
                    {newVelo.setMarque(marque);}  
                
                //rechercher l'ordre de transport correspondant au velo si il existe et le creer. Doit avoir un objet BonDeLivraison et une station de destination
                String requeteSQLODTVelo = "select * from OrdreDeTransport where id ="+donnees.getInt("IDOrdreTransport") +";";
                if (donnees.wasNull() == false){
                    PreparedStatement prepStat3 = connexion.prepareStatement(requeteSQLODTVelo);
                    ResultSet donneesODTVelo = prepStat3.executeQuery();                
                
                    //avant de creer l'odt, on recherche et crée le bon de livraison correspondant.
                    String requeteSQLBonLivraisonVelo = "select * from BonDeLivraison where id ="+donneesODTVelo.getInt("IDLivraison ") +";";
                    PreparedStatement prepStat4 = connexion.prepareStatement(requeteSQLBonLivraisonVelo);
                    ResultSet donneesBonVelo = prepStat3.executeQuery();
                
                    GregorianCalendar dateBonDeLivraison = new GregorianCalendar();
                    dateBonDeLivraison.setTime(donnees.getDate("dateCreation"));
                    BonDeLivraison bonLivraisonODTVelo = new BonDeLivraison(donneesBonVelo.getInt("id"), dateBonDeLivraison, null);
                    
                    //toujour avant de créer l'odt, on cherche et crée sa station de destination (station d'origine = stationvelo)
                    String requeteSQLStatODT = "select * from Station where id ="+donnees.getInt("IDStation") +";";
                    PreparedStatement prepStat5 = connexion.prepareStatement(requeteSQLStatVelo);
                    ResultSet donneesStatODT = prepStat2.executeQuery();
                    Station stationDestination = new Station(donneesStatODT.getInt("id"), donneesStatODT.getString("nomRue"), donneesStatODT.getString("numRue"), donneesStatODT.getInt("codePostal"), donneesStatODT.getString("localite"));
                
                    //On peut ensuite créé l'odt accompagné de son bon de livraison et l'ajouter au velo
                    ODT odtVelo = new ODT(donneesODTVelo.getInt("id"), donneesODTVelo.getBoolean("exceptionnel"), bonLivraisonODTVelo, stationVelo, stationDestination);
                    newVelo.setODT(odtVelo);
                }
                
                veloList.add(newVelo);
            }              
        }
        
        catch(SQLException e){
            throw new BDAccessException(e.getMessage());
        }
        return veloList;
    }
    
    public ArrayList<Velo> getVeloToStation (Station stationRech) throws BDAccessException{ /** 
     * Recherche 1
     * affiche les vélos qui vont être transportés à la station Sélectionnée.
     * Jointures : Station – vélo – ordre de transport. */
        
        ArrayList<Velo> veloList = new ArrayList <Velo>();
        
        try{      
            //recherche principale avec jointure
            int idStationRech = stationRech.getID();
            String requeteSQL = "select v.* from Velo v join OrdreDeTransport odt on v.IDStation = odt.id join station st on odt.IDStation = st.id where st.id = "+idStationRech+";";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            // Note: pas besoin de ? + prepStat, vu qu'on obtient l'info d'une jcombobox, donc pas d'injection sql.
            
            ResultSet donnees = prepStat.executeQuery();
            ResultSetMetaData metaVeloList = donnees.getMetaData( );
        
            while(donnees.next()){ //creation d'un ojet velo devant également contenir une station d'origine et un ordre de transport
                
                GregorianCalendar dateAchat = new GregorianCalendar();
                dateAchat.setTime(donnees.getDate("dateAchat"));               
                
                //rechercher une station à partire de l'idStation du vélo et le créer pour l'ajouter à l'objet velo
                //Attention ! Cette station est l'ancienne station du vélo (présente dans l'objet vélo) et pas celle ou il est transporté (StationRech)
                String requeteSQLStatVelo = "select * from Station where id ="+donnees.getInt("IDStation") +";";
                PreparedStatement prepStat2 = connexion.prepareStatement(requeteSQLStatVelo);
                ResultSet donneesStatVelo = prepStat2.executeQuery();
                Station stationVelo = new Station(donneesStatVelo.getInt("id"), donneesStatVelo.getString("nomRue"), donneesStatVelo.getString("numRue"), donneesStatVelo.getInt("codePostal"), donneesStatVelo.getString("localite"));
                
                Velo newVelo = new Velo (donnees.getInt("id"), dateAchat, stationVelo);
                
                String marque = donnees.getString("marque");
                if (donnees.wasNull( ) == false)
                    {newVelo.setMarque(marque);}
                
                // si le velo possède un odt => création de celui ci ainsi que de son bon de livraison
                String requeteSQLODTVelo = "select * from OrdreDeTransport where id ="+donnees.getInt("IDOrdreTransport") +";";
                if (donnees.wasNull() == false){
                    PreparedStatement prepStat3 = connexion.prepareStatement(requeteSQLODTVelo);
                    ResultSet donneesODTVelo = prepStat3.executeQuery();                
                
                    //avant de creer l'odt, on recherche et crée le bon de livraison correspondant.
                    String requeteSQLBonLivraisonVelo = "select * from BonDeLivraison where id ="+donneesODTVelo.getInt("IDLivraison ") +";";
                    PreparedStatement prepStat4 = connexion.prepareStatement(requeteSQLBonLivraisonVelo);
                    ResultSet donneesBonVelo = prepStat3.executeQuery();
                
                    GregorianCalendar dateBonDeLivraison = new GregorianCalendar();
                    dateBonDeLivraison.setTime(donnees.getDate("dateCreation"));
                    
                    BonDeLivraison bonLivraisonODTVelo = new BonDeLivraison(donneesBonVelo.getInt("id"), dateBonDeLivraison, null);
                
                    //On peut ensuite créé l'odt accompagné de son bon de livraison et l'ajouter au velo
                    ODT odtVelo = new ODT(donneesODTVelo.getInt("id"), donneesODTVelo.getBoolean("exceptionnel"), bonLivraisonODTVelo, stationVelo, stationRech);
                    newVelo.setODT(odtVelo);
                }
                
                // holy shit, toutes ces étapes sont trop chiantes ! y a pas moyen de simplifier avec getObject() ?                
                
                //finalement, on ajoute l'objet Velo dans l'arrayList... Courrage, les autres recherches sont plus courtes. Si vous avez des questions, n'hésitez pas ;)
                veloList.add(newVelo);
            }  
            
        }
        catch(Exception e){
            throw new BDAccessException(e.getMessage());
        }
        return veloList;
    }
    
    public ArrayList<Reparateur> getDernierReparateurVelo (int idVelo) throws BDAccessException{ /**
     * Recherche 2
     * affiche les 3 derniers réparateurs du velo choisit.
     * Jointures: Vélo - ordre de réparation - réparateur 
     */
        ArrayList<Reparateur> reparateurList = new ArrayList <Reparateur>();
        int i = 0;
         
        try{      
            //recherche avec jointure
            String requeteSQL = "select r.* from velo v join ordreDeReparation odr on v.id = odr.IDVelo join reparateur r on odr.IDReparateur = r.id where v.id = "+idVelo+";";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            ResultSet donnees = prepStat.executeQuery();
            ResultSetMetaData metaReparateurList = donnees.getMetaData( );
            
            while(donnees.next() && i<3){                
                
                GregorianCalendar dateNaissance = new GregorianCalendar();
                dateNaissance.setTime(donnees.getDate("dateNaissance"));
                
                GregorianCalendar dateRecrutement = new GregorianCalendar();
                dateRecrutement.setTime(donnees.getDate("dateAchat"));
                
                // on crée l'objet Reparateur et on l'ajoute à l'ArrayList                
                Reparateur newReparateur = new Reparateur(donnees.getInt("id"), donnees.getString("diplome"), donnees.getString("nom"),donnees.getString("prenom"), dateNaissance, dateRecrutement, donnees.getString("adresse"), donnees.getInt("salaire") );
                reparateurList.add(newReparateur);
                i++;
            }  
            
        }
        catch(Exception e){
            throw new BDAccessException(e.getMessage());
        }
        return reparateurList;
    }
    
    public int getNbrVeloRepare (GregorianCalendar dateDebut, GregorianCalendar dateFin, Station stationRech)throws BDAccessException, InputException{/**
     * recherche 3
     * Renvoyer le nombre de réparations faites à tous les vélos de la station entre deux dates.
     * Jointure: Station - Vélo - Ordre de réparation.
     */
        int nbVelo = 0;
        
        if(dateDebut.compareTo(dateFin)>0){
            throw new InputException("Error: La date de début entrée est ultérieure à la date de fin");
        }
        else {
        try{
            //le java.sql avant le .Date est utilisé car la classe Date apparais dans java.util et java.sql
            java.sql.Date dateDebutsql = new java.sql.Date(dateDebut.getTimeInMillis());
            java.sql.Date dateFinsql = new java.sql.Date(dateFin.getTimeInMillis());
            int idStationRech = stationRech.getID();
            
            //to do: modifier l'utilisation des datesql
            String requeteSQL = " select odr.* from Station st join velo v on st.id = v.IDStation join OrdreDeReparation odr on v.id = odr.IDVelo where st.id = "+ idStationRech +" and odr.date between "+ dateDebutsql +" and "+dateFinsql+" ;";
            PreparedStatement prepStat = connexion.prepareStatement(requeteSQL);
            ResultSet donnees = prepStat.executeQuery();
            
            while(donnees.next()){ 
                nbVelo++;
            }  
            
        }
        catch(Exception e){
            throw new BDAccessException(e.getMessage());
        }            
        }
        return nbVelo;
    }
    
}
