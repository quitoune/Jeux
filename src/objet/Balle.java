/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import java.awt.Color;
import java.awt.Graphics;
import utils.Utils;

/**
 *
 * @author titi
 */
public class Balle extends Element {
    public Balle(Color couleur, int abscisse, int ordonnee, int longueur){
        super(couleur, abscisse, ordonnee, longueur);
    }
    
    public Balle(){
        super();
    }
    
    @Override
    public void dessiner(Graphics g) {
    	if(!Utils.colorEquals(this.getCouleur(), Color.BLACK)) {
            g.setColor(couleur);
            g.fillOval(abscisse,ordonnee, longueur, longueur);
    	}
    }
    
    public Balle random(int a, int x, int y, int l){
        return new Balle (Utils.randomColor(a), x, y, l);
    }
}
