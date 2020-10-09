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
import javax.swing.JFrame;

/**
 *
 * @author titi
 */
public class Bubble {
    private Plateau lePlateau;
    private Config config;
    private int score;
    static protected final String path = "resources/bubble/";
    
    public Bubble(){
        config = new Config();
        config.getConfig();
        lePlateau = new Plateau(config.getNombreDeCouleur());
        score = 0;
    }
    
    public Bubble(int nombreDeCouleur, boolean gravite, boolean ajoutCol){
        config = new Config(nombreDeCouleur, gravite, ajoutCol);
        lePlateau = new Plateau(config.getNombreDeCouleur());
        score = 0;
    }
    
    public Plateau getPlateau(){
        return lePlateau;
    }
    
    public void setPlateau(Plateau plateau){
        lePlateau = plateau;
    }
    
    public Config getConfig(){
        return config;
    }
    
    public void setConfig(Config config){
        this.config = config;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int point){
        score = score + point * (point - 1);
    }
    
    public int update(int x, int y){
        if(!lePlateau.getPlace(x, y).getCouleur().equals(Color.BLACK)){
            if(lePlateau.aDesVoisins(x, y)){
                CollectionBulle ensemble = lePlateau.ensembleVoisins(x, y);
                this.setScore(ensemble.size());
                for(int k = 0; k < ensemble.size(); k++){
                    lePlateau.supprimer((Bulle) ensemble.get(k));
                }
                lePlateau.bas();
                if(config.getAjoutCol()){
                    lePlateau.ajouterColonne(config.getNombreDeCouleur());
                }
                if(config.getGravite()){
                    lePlateau.droite();
                }
                if(!lePlateau.restePossibilites()){
                    System.out.println("*************************************************");
                    System.out.println("Score final: " + this.getScore());
                    System.out.println("*************************************************");
                    if(this.updateClassement()){
                        return 2;
                    } else {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    
    public void stop(int i, java.awt.Label label, javax.swing.JPanel panel){
        label.setText(Integer.toString(this.getScore()));
        panel.repaint();
        String title = "Partie terminée";
        int type = JOptionPane.INFORMATION_MESSAGE;
        String text = "Score final: " + this.getScore();
        switch(i){
            case 2:
                text = "Nouveau meilleur score\n" + text;
                JOptionPane.showMessageDialog(null, text, title, type, null);
                this.restart(label, panel);
                break;
            case 1:
                JOptionPane.showMessageDialog(null, text, title, type, null);
                this.restart(label, panel);
                break;
            default:
                break;
        }
    }
    
    public void restart(java.awt.Label label, javax.swing.JPanel panel){
        System.out.println("Nombre de couleur: " + config.getNombreDeCouleur() + " - Gravité: " + config.getGravite() + " - Ajout de colonne: " + config.getAjoutCol());
        this.setPlateau(new Plateau(config.getNombreDeCouleur()));
        this.score = 0;
        label.setText(Integer.toString(this.getScore()));
        panel.repaint();
    }
    
    public void dessiner(Graphics g){
        lePlateau.dessiner(g);
    }
    
    public String fileName(Config config){
        return Bubble.path + "stats/" + this.convert(config.getGravite()) + "gravite_" + this.convert(config.getAjoutCol()) + "ajout_" + config.getNombreDeCouleur() + ".txt";
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
            f = new FileWriter(Bubble.path + "game.txt");
            f.write(config.getNombreDeCouleur() + "\n");
            f.write(config.getGravite() + "\n");
            f.write(config.getAjoutCol() + "\n");
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
    
    public void charger(javax.swing.JPanel panel){
        BufferedReader f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new BufferedReader(new FileReader(Bubble.path + "game.txt"));
            config.setNombreDeCouleur(Integer.parseInt(f.readLine()));
            config.setGravite(Boolean.parseBoolean(f.readLine()));
            config.setAjoutCol(Boolean.parseBoolean(f.readLine()));
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
        panel.repaint();
    }
    
    public void aide(){
        BufferedReader f;
        String line;
        String message = "";
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new BufferedReader(new FileReader(Bubble.path + "aide.txt"));
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
    
    public Integer[] getClassement(Config config){
        BufferedReader f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new BufferedReader(new FileReader(this.fileName(config)));
            Integer[] values = Utils.getData(f.readLine());
            f.close();
            return values;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Integer[0];
    }
    
    public void afficherClassement(Config config){
        Integer[] values = this.getClassement(config);
        String message = "";
        for(int k = 0; k < 10; k++){
            message += (k + 1) + ". " + values[k] + "\n";
        }
        JOptionPane.showMessageDialog(null, message, "Classement", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean updateClassement(){
        boolean new_score = false;
        Integer[] values = this.getClassement(config);
        for(int k = 0; k < 10; k++){
            if(values[k] <= this.score){
                for(int j = 9; k <= j; j--){
                    if(j == k){
                        values[j] = this.score;
                        new_score = true;
                    } else {
                        values[j] = values[j - 1];
                    }
                }
            }
            if(new_score){
                this.updateClassement(values);
                return new_score;
            }
        }
        return new_score;
    }
    
    public void updateClassement(Integer[] values){
        FileWriter f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new FileWriter(this.fileName(config));
            f.write(Utils.join(";", values));
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updateConfig(JFrame frame, java.awt.Label label, javax.swing.JPanel panel){
        ConfigBox box = new ConfigBox(frame, "Configuration", true, this.config);
        Config new_config = box.afficher();
        if(!config.equals(new_config)){
            this.setConfig(new_config);
            this.getConfig().saveConfig();
            this.restart(label, panel);
        }
    }
}
