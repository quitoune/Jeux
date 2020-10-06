/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import java.util.ArrayList;

/**
 *
 * @author titi
 */
public class Objet {
    public void create(ArrayList<String> lines){
        for(int k = 0; k < lines.size(); k++){
            System.out.println(lines.get(k));
        }
    }
    
    public void create(String line){
        System.out.println(line);
    }
}
