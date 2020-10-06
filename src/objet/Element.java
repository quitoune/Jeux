/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.Fichier;
import utils.Utils;

/**
 *
 * @author titi
 */
public class Element extends Objet {
    protected Color couleur;
    protected int abscisse, ordonnee, longueur;
    
    public Element(Color couleur, int abscisse, int ordonnee, int longueur){
        this.couleur = couleur;
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
        this.longueur = longueur;
    }
    
    public Element(){
        couleur = new Color(0, 0, 0);
        abscisse = 0;
        ordonnee = 0;
        longueur = 0;
    }
    
    public Color getCouleur(){
        return couleur;
    }
    
    public int getRed(){
        return couleur.getRed();
    }
    
    public int getBlue(){
        return couleur.getBlue();
    }
    
    public int getGreen(){
        return couleur.getGreen();
    }
    
    public void setCouleur(Color couleur){
        this.couleur = couleur;
    }
    
    public int getAbscisse(){
        return abscisse;
    }
    
    public void setAbscisse(int abscisse){
        this.abscisse = abscisse;
    }
    
    public int getOrdonnee(){
        return ordonnee;
    }
    
    public void setOrdonnee(int ordonnee){
        this.ordonnee = ordonnee;
    }
    
    public int getLongueur(){
        return longueur;
    }
    
    public void setLongueur(int longueur){
        this.longueur = longueur;
    }
    
    public boolean equalsTo(Element element){
        if(!Utils.colorEquals(this.couleur, element.getCouleur())){
            return false;
        } else if(this.abscisse != element.getAbscisse()){
            return false;
        } else if(this.ordonnee != element.getOrdonnee()){
            return false;
        } else {
            return this.longueur != element.getLongueur();
        }
    }

    public void dessiner (Graphics g){
        
    }
    
    @Override
    public void create(ArrayList<String> lines){
        this.create(lines.get(0));
    }
    
    @Override
    public void create(String line){
        Integer[] values = Utils.getData(line);
        couleur = new Color(values[0], values[1], values[2]);
        abscisse = values[3];
        ordonnee = values[4];
        longueur = values[5];
    }
    
    public void saveInFile(String nomDuFichier) {
        String info = this.getAttributes();
        Fichier.ecrire(nomDuFichier, info);
    }
    
    public String getAttributes(){
        return this.getRed() + ";" + this.getGreen() + ";" + this.getBlue() + ";" + abscisse + ";" + ordonnee + ";" + longueur;
    }
}
