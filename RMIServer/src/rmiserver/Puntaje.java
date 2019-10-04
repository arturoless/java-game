/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.io.Serializable;

/**
 *
 * @author Arturo Lessieur
 */
public class Puntaje implements Serializable {
    private static final long serialVersionUID = 1160476516911661470L;
    private int puntaje = 0;
    
    public void compararPuntaje(int puntos){
        if (puntos > puntaje){
            puntaje=puntos;
        };
    }
    
    public int getPuntaje(){
        return puntaje;
    }
    
}
