/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubble;

import collection.CollectionBulle;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import objet.Bulle;
import objet.Plateau;
import utils.Utils;

/**
 *
 * @author titi
 */
public class Bubble {
    private Plateau lePlateau;
    private int nombreDeCouleur;
    private int score;
    private boolean gravite, ajoutCol;
    
    public Bubble(){
        nombreDeCouleur = 3;
        lePlateau = new Plateau(nombreDeCouleur);
        score = 0;
        gravite = false;
        ajoutCol = true;
    }
    
    public Bubble(int nombreDeCouleur, boolean gravite, boolean ajoutCol){
        lePlateau = new Plateau(nombreDeCouleur);
        this.nombreDeCouleur = nombreDeCouleur;
        this.ajoutCol = ajoutCol;
        this.gravite = gravite;
        score = 0;
    }
    
    public Plateau getPlateau(){
        return lePlateau;
    }
    
    public void setPlateau(Plateau plateau){
        lePlateau = plateau;
    }
    
    public int getNombreDeCouleur(){
        return nombreDeCouleur;
    }
    
    public void setNombreDeCouleur(int nombre){
        this.nombreDeCouleur = nombre;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int point){
        score = score + point * (point - 1);
    }
    
    public boolean getGravite(){
        return gravite;
    }
    
    public void setGravite(boolean gravite){
        this.gravite = gravite;
    }
    
    public boolean getAjoutCol(){
        return ajoutCol;
    }
    
    public void setAjoutCol(boolean ajoutCol){
        this.ajoutCol = ajoutCol;
    }
    
    public void update(int x, int y){
        if(!lePlateau.getPlace(x, y).getCouleur().equals(Color.BLACK)){
            if(lePlateau.aDesVoisins(x, y)){
                CollectionBulle ensemble = lePlateau.ensembleVoisins(x, y);
                this.setScore(ensemble.size());
                for(int k = 0; k < ensemble.size(); k++){
                    lePlateau.supprimer((Bulle) ensemble.get(k));
                }
                lePlateau.bas();
                if(ajoutCol){
                    lePlateau.ajouterColonne(nombreDeCouleur);
                }
                if(gravite){
                    lePlateau.droite();
                }
                if(!lePlateau.restePossibilites()){
                    System.out.println("Fin");
                }
            }
        }
    }
    
    public void restart(){
        System.out.println("Nombre de couleur: " + nombreDeCouleur + " - Gravité: " + gravite + " - Ajout de colonne: " + ajoutCol);
        this.setPlateau(new Plateau(nombreDeCouleur));
        this.score = 0;
    }
    
    public void dessiner(Graphics g){
        lePlateau.dessiner(g);
    }
    
    public String fileName(){
        return "resources/" + this.convert(gravite) + "gravite_" + this.convert(ajoutCol) + "ajout_" + nombreDeCouleur;
    }
    
    public String convert(boolean bool){
        if(bool){
            return "y";
        } else {
            return "n";
        }
    }
    
    public void enregistrer(){
        FileWriter f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new FileWriter("resources/game.txt");
            f.write(nombreDeCouleur + "\n");
            f.write(gravite + "\n");
            f.write(ajoutCol + "\n");
            f.write(score + "\n");
            for(int k = 0; k < 10; k++){
                for(int j = 0; j < 10; j++){
                    Bulle bulle = lePlateau.getPlace(k, j);
                    f.write(bulle.getRed() + ";" + bulle.getGreen() + ";" + bulle.getBlue() + ";" + k + ";" + j + "\n");
                }
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void charger(){
        BufferedReader f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new BufferedReader(new FileReader("resources/game.txt"));
            nombreDeCouleur = Integer.parseInt(f.readLine());
            gravite = Boolean.parseBoolean(f.readLine());
            ajoutCol = Boolean.parseBoolean(f.readLine());
            score = Integer.parseInt(f.readLine());
            for(int k = 0; k < 10; k++){
                for(int j = 0; j < 10; j++){
                    Integer[] values = Utils.getData(f.readLine());
                    lePlateau.getPlace(k, j).setCouleur(new Color(values[0], values[1], values[2]));
                }
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void aide(){
        BufferedReader f;
        String line;
        String message = "";
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new BufferedReader(new FileReader("resources/aide.txt"));
            line = f.readLine();
            while(line != null && !"".equals(line)){
                message += line + "\n";
                line = f.readLine();
            }
            JOptionPane.showMessageDialog(null, message, "Aide", JOptionPane.INFORMATION_MESSAGE);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}