/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import java.awt.Color;
import utils.Utils;

/**
 *
 * @author titi
 */
public class Bulle extends Balle {
    static public int diametre = 26;
    
    public Bulle(Color couleur, int abscisse, int ordonnee){
        super(couleur, abscisse, ordonnee, Bulle.diametre - 1);
    }
    
    public Bulle(){
        super();
        longueur = Bulle.diametre - 1;
    }
    
    public Bulle random(int a, int x, int y){
        return new Bulle (Utils.randomColor(a), x, y);
    }
}
