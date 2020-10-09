/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author titi
 */
public class Config {
    protected int nombreDeCouleur;
    protected boolean gravite, ajoutCol;
    
    public Config(int nombreDeCouleur, boolean gravite, boolean ajoutCol){
        this.gravite = gravite;
        this.ajoutCol = ajoutCol;
        this.nombreDeCouleur = nombreDeCouleur;
    }
    
    public Config(){
        gravite = false;
        ajoutCol = true;
        nombreDeCouleur = 3;
    }
    
    public int getNombreDeCouleur(){
        return nombreDeCouleur;
    }
    
    public void setNombreDeCouleur(int nombreDeCouleur){
        this.nombreDeCouleur = nombreDeCouleur;
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
    
    @SuppressWarnings("CallToPrintStackTrace")
    public void getConfig(){
        BufferedReader f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new BufferedReader(new FileReader(Bubble.path + "config.txt"));
            nombreDeCouleur = Integer.parseInt(f.readLine());
            gravite = Boolean.parseBoolean(f.readLine());
            ajoutCol = Boolean.parseBoolean(f.readLine());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public void saveConfig(){
        FileWriter f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new FileWriter(Bubble.path + "config.txt");
            f.write(nombreDeCouleur + "\n");
            f.write(gravite + "\n");
            f.write(ajoutCol + "\n");
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean equals(Config config){
        if(config.gravite != this.gravite){
            return false;
        } else if(config.ajoutCol != this.ajoutCol){
            return false;
        } else {
            return config.nombreDeCouleur == this.nombreDeCouleur;
        }
    }
}
