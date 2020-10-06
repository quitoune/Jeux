/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.ArrayList;
import objet.Objet;

/**
 *
 * @author titi
 * @param <Truc>
 */
public class Collection<Truc extends Objet> extends ArrayList<Truc> {
    /**
     * construit moi-meme comme une collection d'objet appartenant a la classe
     * nomClasse
     */
    public Collection() {
        super();
    }

    private Collection(Collection<Truc> liste) {
        this.clear();
        this.addAll(liste);
    }
        
    public void ajouter (Collection liste){
        for (int k = 0; k <= liste.size()-1; k++){
            this.add((Truc) liste.get(k));
        }
    }
    
    public void simplifier (){
        Collection liste = new Collection(this);
        this.clear();
        for (int k = 0; k <= liste.size()-1; k++){
            if (!this.contains((Truc) liste.get(k))){
                this.add((Truc) liste.get(k));
            }
        }
    }
    
    public void create(ArrayList<String> lines){
        for(int k = 0; k < lines.size(); k++){
            System.out.println(lines.get(k));
        }
    }
}
