/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import collection.CollectionBulle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.Fichier;
import utils.Utils;

/**
 *
 * @author titi
 */
public class Plateau extends Objet {
    private Bulle[][] place;
    
    public Plateau(int a) {
        Bulle bulle = new Bulle();
        place = new Bulle[10][10];
        for(int k = 0; k < 10; k++){
            for(int j = 0; j < 10; j++){
                place[k][j] = bulle.random(a, Bulle.diametre*k, Bulle.diametre*j);
            }
        }
    }
    
    public Plateau() {
        place = new Bulle[10][10];
        for(int k = 0; k < 10; k++){
            for(int j = 0; j < 10; j++){
                place[k][j] = new Bulle(Color.BLACK, Bulle.diametre*k, Bulle.diametre*j);
            }
        }
    }

    public Bulle[][] getPlace() {
        return place;
    }

    public void setPlace(Bulle[][] place) {
        this.place = place;
    }
    
    public Bulle getPlace(int x, int y) {
        return place[x][y];
    }

    public void setPlace(Bulle bulle) {
        int x = bulle.getAbscisse() / Bulle.diametre;
        int y = bulle.getOrdonnee() / Bulle.diametre;
        this.place[x][y] = bulle;
    }
    
    public void dessiner(Graphics g){
        for(int k = 0; k < 10; k++){
            for(int j = 0; j < 10; j++){
                place[k][j].dessiner(g);
            }
        }
    }
    
    public void supprimer(CollectionBulle liste){
        for(int k = 0; k < liste.size(); k++){
            this.supprimer((Bulle) liste.get(k));
        }
    }
    
    public void supprimer(Bulle bulle){
        int x = bulle.getAbscisse() / Bulle.diametre;
        int y = bulle.getOrdonnee() / Bulle.diametre;
        this.supprimer(x, y);
    }
    
    public void supprimer(int x, int y){
        place[x][y].setCouleur(Color.BLACK);
    }
    
    /**
     * Vérifie si pour une position, il y a des voisins 
     * @param x
     * @param y
     * @return 
     */
    public boolean aDesVoisins(int x, int y){
        return this.aDesVoisins(this.getPlace(x, y));
    }
    
    /**
     * Vérifie si une bulle à des voisins 
     * @param bulle
     * @return 
     */
    public boolean aDesVoisins(Bulle bulle){
        CollectionBulle liste = this.getVoisins(bulle);
        return (liste.size() > 0);
    }
    
    /**
     * Récupération des voisins d'une position
     * @param x
     * @param y
     * @return 
     */
    public CollectionBulle getVoisins(int x, int y){
        return this.getVoisins(this.getPlace(x, y));
    }
    
    /**
     * Récupération des voisins d'une bulle
     * @param bulle
     * @return 
     */
    public CollectionBulle getVoisins(Bulle bulle){
        CollectionBulle voisins = new CollectionBulle();
        Color couleur = bulle.getCouleur();
        int x = bulle.getAbscisse() / Bulle.diametre;
        int y = bulle.getOrdonnee() / Bulle.diametre;
        
        if(x < 0 || y < 0 || 9 < x || 9 < y){
            return voisins;
        }
        
        if(0 < x){
            if(Utils.colorEquals(couleur, place[x-1][y].getCouleur())){
                voisins.add(place[x-1][y]);
            }
        }
        
        if(0 < y){
            if(Utils.colorEquals(couleur, place[x][y-1].getCouleur())){
                voisins.add(place[x][y-1]);
            }
        }
        
        if(x < 9){
            if(Utils.colorEquals(couleur, place[x+1][y].getCouleur())){
                voisins.add(place[x+1][y]);
            }
        }
        
        if(y < 9){
            if(Utils.colorEquals(couleur, place[x][y+1].getCouleur())){
                voisins.add(place[x][y+1]);
            }
        }
        
        return voisins;
    }
    
    /**
     * Récupération de l'ensemble des bulles collées à une position
     * @param x
     * @param y
     * @return 
     */
    public CollectionBulle ensembleVoisins(int x, int y){
        CollectionBulle liste = new CollectionBulle();
        CollectionBulle voisins;
        
        if(this.aDesVoisins(x, y)){
            liste.add(this.getPlace(x, y));
            int k = 0;
            
            while(k < liste.size()){
                voisins = this.getVoisins((Bulle) liste.get(k));
                for (Object voisin : voisins) {
                    if(!liste.contains(voisin)){
                        liste.add(voisin);
                    }
                }
                k++;
            }
        }
        return liste;
    }
    
    /**
     * Vérification s'il reste des bulles collées ayant la même couleur 
     * @return 
     */
    public boolean restePossibilites(){
        for(int k = 9; 0 <= k; k--){
            for(int j = 9; 0 <= j; j--){
                if(!Utils.colorEquals(place[k][j].getCouleur(), Color.BLACK)){
                    if(this.aDesVoisins(place[k][j])){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Rabattre les bulles vers le bas
     */
    public void bas(){
        boolean keep;
        int i;
        for(int k = 9; 0 <= k; k--){
            for(int j = 9; 0 < j; j--){
                if(this.getPlace(k, j).getCouleur().equals(Color.BLACK)){
                    keep = true;
                    i = j - 1;
                    while(0 <= i && keep){
                        if(!this.getPlace(k, i).getCouleur().equals(Color.BLACK)){
                            this.deplacerBulle(k, j, k, i);
                            keep = false;
                        }
                        i--;
                    }
                }
            }
        }
    }
    
    /**
     * Rabbatre les bulles vers la droite
     */
    public void droite(){
        boolean keep;
        int i;
        for(int j = 9; 0 <= j; j--){
            for(int k = 9; 0 < k; k--){
                if(this.getPlace(k, j).getCouleur().equals(Color.BLACK)){
                    keep = true;
                    i = k - 1;
                    while(0 <= i && keep){
                        if(!this.getPlace(i, j).getCouleur().equals(Color.BLACK)){
                            this.deplacerBulle(k, j, i, j);
                            keep = false;
                        }
                        i--;
                    }
                }
            }
        }
    }
    
    public int nombreColonneVide(){
        int nombre = 0;
        for(int k = 0; k < 10; k++){
            if(this.getPlace(k, 9).getCouleur().equals(Color.BLACK)){
                nombre++;
            }
        }
        return nombre;
    }
    
    /**
     * Ajouter nbr colonnes
     * @param a 
     */
    public void ajouterColonne(int a){
        this.deplacerColonneDroite();
        int nombre = this.nombreColonneVide();
    	for(int j = 0; j < nombre; j++) {
            for (int k = 0; k < 10; k++){
                place[j][k].setCouleur(Utils.randomColor(a));
            }
    	}
    }
    
    /**
     * Déplacer les colonnes vers la droite
     */
    public void deplacerColonneDroite(){
        boolean keep;
        int j;
        for(int k = 9; k > 0; k--){
            if(this.getPlace(k, 9).getCouleur().equals(Color.BLACK)){
                keep = true;
                j = k - 1;
                while(0 <= j && keep){
                    if(!this.getPlace(j, 9).getCouleur().equals(Color.BLACK)){
                        this.deplacerColonne(k, j);
                        keep = false;
                    }
                    j--;
                }
            }
        }
    }
    
    public void deplacerBulle(int newCol, int newRow, int oldCol, int oldRow){
        place[newCol][newRow].setCouleur(place[oldCol][oldRow].getCouleur());
        place[oldCol][oldRow].setCouleur(Color.BLACK);
    }
    
    /**
     * Déplacement de la colonne col - 1 dans la colonne col
     * @param col 
     */
    public void deplacerColonne(int col){
        this.deplacerColonne(col, col - 1);
    }
    
    /**
     * Déplacement de la colonne oldCol dans la colonne newCol
     * @param newCol
     * @param oldCol 
     */
    public void deplacerColonne(int newCol, int oldCol){
        for(int k = 0; k < 10; k++){
            place[newCol][k].setCouleur(place[oldCol][k].getCouleur());
            place[oldCol][k].setCouleur(Color.BLACK);
        }
    }
    
    /**
     * Récupération des valeurs de chaque bulle du plateau
     * @return 
     */
    public String getAttributes(){
        String attributes = "";
        for(int k = 0; k < 10; k++){
            for(int j = 0; j < 10; j++){
                attributes += place[k][j].getRed() + ";";
                attributes += place[k][j].getGreen() + ";";
                attributes += place[k][j].getBlue() + ";";
                attributes += k + ";" + j + "\n";
            }
        }
        return attributes;
    }
}
