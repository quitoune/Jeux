/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import objet.Balle;
import objet.Bulle;
import objet.Element;
import bubble.Bubble;
import utils.Utils;
import java.awt.Color;

/**
 *
 * @author titi
 */
public class TestElement {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Element element = new Element(new Color(255, 255, 255), 21, 21, 35);
        System.out.println("Element");
        System.out.println("Couleur: (" + element.getRed() + ", " + element.getBlue() + ", " + element.getGreen() + ")");
        System.out.println("Position: [" + element.getAbscisse() + ", " + element.getOrdonnee() + "]");
        System.out.println("Longueur: " + element.getLongueur());
        System.out.println("************************************");
        
        Balle balle = new Balle(new Color(255, 0, 255), 30, 30, 50);
        System.out.println("Balle");
        System.out.println("Couleur: (" + balle.getRed() + ", " + balle.getBlue() + ", " + balle.getGreen() + ")");
        System.out.println("Position: [" + balle.getAbscisse() + ", " + balle.getOrdonnee() + "]");
        System.out.println("Longueur: " + balle.getLongueur());
        System.out.println("************************************");
        
        Bulle bulle = new Bulle(new Color(255, 0, 255), 21, 21);
        System.out.println("Bulle");
        System.out.println("Couleur: (" + bulle.getRed() + ", " + bulle.getBlue() + ", " + bulle.getGreen() + ")");
        System.out.println("Position: [" + bulle.getAbscisse() + ", " + bulle.getOrdonnee() + "]");
        System.out.println("Longueur: " + bulle.getLongueur());
        System.out.println("************************************");
        
        Bulle randomBulle = bulle.random(5, 50, 50);
        System.out.println("Random Bulle");
        System.out.println("Couleur: (" + randomBulle.getRed() + ", " + randomBulle.getBlue() + ", " + randomBulle.getGreen() + ")");
        System.out.println("Position: [" + randomBulle.getAbscisse() + ", " + randomBulle.getOrdonnee() + "]");
        System.out.println("Longueur: " + randomBulle.getLongueur());
        System.out.println("************************************");
        
        Integer[] values = {492, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        String join = Utils.join(";", values);
        System.out.println(join);
    }
}
