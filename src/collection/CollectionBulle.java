/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import objet.Bulle;

/**
 *
 * @author titi
 */
public class CollectionBulle extends Collection {
    public CollectionBulle(int nombre){
        this.clear();
        Bulle bulle = new Bulle();
        for(int k = 0; k < nombre; k++){
            this.add(bulle.random(8, k, k));
        }
    }
    
    public CollectionBulle(){
        
    }
}
