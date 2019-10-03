/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Arturo Lessieur
 */
public class Puntaje {
    private int puntaje = 5;
    
    public void incrementarPuntaje(){
        puntaje++;
    }
    
    public void decrementarPuntaje(){
        puntaje--;
    }
    
    public void resetearPuntaje(){
        puntaje=5;
    }
    
    public int getPuntaje(){
        return puntaje;
    }
    
}
