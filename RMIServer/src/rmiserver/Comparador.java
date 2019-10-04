/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arturo Lessieur
 */
public class Comparador extends Thread{
     private final Semaphore mutex;
     private final Puntaje puntaje;
     private final int puntos;
    
    public Comparador(Semaphore mutex, Puntaje puntaje, int puntos){
        this.mutex=mutex;
        this.puntaje=puntaje;
        this.puntos=puntos;
    }
    
    @Override
    public void run(){
         try {
             mutex.acquire();
             puntaje.compararPuntaje(puntos);
             mutex.release();
         } catch (InterruptedException ex) {
             Logger.getLogger(Comparador.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
}
