/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;

/**
 *
 * @author titi
 */
public class Utils {
    static public boolean colorEquals(Color color, Color couleur){
        if(color.getBlue() != couleur.getBlue()){
            return false;
        } else if(color.getGreen() != couleur.getGreen()){
            return false;
        } else {
            return color.getRed() == couleur.getRed();
        }
    }
    
    static public Color randomColor(int a){
        Color couleur = new Color(0,0,0);
        int n = (int) (Math.random() * (a));
        switch (n) {
            case 0:
                couleur = new Color(255, 0, 0); //rouge
                break;
            case 1:
                couleur = new Color(0, 0, 255); //bleu
                break;
            case 2:
                couleur = new Color(255, 255, 0); //jaune
                break;
            case 3:
                couleur = new Color(0, 255, 0); //vert
                break;
            case 4:
                couleur = new Color(137,77,167); //violet
                break;
            case 5:
                couleur = new Color(244, 102, 27); //orange
                break;
            case 6:
                couleur = new Color(245, 245, 245); //gris
                break;
            case 7:
                couleur = new Color(255, 0, 255); //rose
                break;
            default:
                break;
        }
        return couleur;
    }
    
    static public Integer[] getData(String line){
        String[] values = line.split(";", -1);
        Integer[] datas = new Integer[values.length];
        for(int k = 0; k < values.length; k++){
            if(!"".equals(values[k])){
                datas[k] = Integer.parseInt(values[k]);
            }
        }
        return datas;
    }
    
    static public String join(String delimiter, Integer[] values){
        String join = "";
        for(int k = 0; k < values.length; k++){
            join += values[k];
            if(k != values.length - 1){
                join += delimiter;
            }
        }
        return join;
    }
}
