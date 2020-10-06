/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import collection.CollectionBulle;
import objet.Bulle;

/**
 *
 * @author titi
 */
public class TestCollection {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CollectionBulle liste = new CollectionBulle(4);
        liste.add((Bulle) liste.get(3));
        for(int k = 0; k < liste.size(); k++){
            Bulle bulle = (Bulle) liste.get(k);
            System.out.println(bulle.getAttributes());
        }
        
        System.out.println("**********************************************");
        
        liste.simplifier();
        for(int k = 0; k < liste.size(); k++){
            Bulle bulle = (Bulle) liste.get(k);
            System.out.println(bulle.getAttributes());
        }
    }
}
