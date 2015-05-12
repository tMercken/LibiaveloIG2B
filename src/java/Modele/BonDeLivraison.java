/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.GregorianCalendar;

/**
 *
 * @author Quentin
 */
public class BonDeLivraison {
    private int id;
    private GregorianCalendar dateCreation;
    private Livreur liv;
    
    public BonDeLivraison(int i, GregorianCalendar date, Livreur l)
    {
        id = i;
        dateCreation = date;
        liv = l;
    }
}
