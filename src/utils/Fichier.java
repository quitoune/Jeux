/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import objet.Objet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author titi
 */
public class Fichier {
    static public void lire(String nomDuFichier) {
        String classe, line;
        Objet O;
        BufferedReader f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new BufferedReader(new FileReader(nomDuFichier));
            classe = f.readLine();
            O = (Objet) Class.forName("objet." + classe).newInstance();
            ArrayList<String> lines = new ArrayList();
            line = f.readLine();
            while(line != null && !"".equals(line)){
                lines.add(line);
                line = f.readLine();
            }
            f.close();
            O.create(lines);
        } catch (ClassNotFoundException e) {
            System.out.println(" Erreur de lecture : la classe n'existe pas");
            System.out.println("Exception: " + e.getMessage());
            System.exit(1);
        } catch (InstantiationException e) {
            System.out.println(" Erreur de lecture : la classe est abstract ou est une interface ou n'a pas de constructeur accessible sans paramètre");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println(" Erreur de lecture : la classe n'est pas accessible");
            System.exit(1);
        } catch (IOException e) {
        }
    }
    
    static public void ecrire(String nomDuFichier, String line) {
        FileWriter f;
        try {
            // doit etre utilise sous surveillance de la levee d'exception
            f = new FileWriter(nomDuFichier);
            f.write(line);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
