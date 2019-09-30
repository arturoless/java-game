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
public class Incrementador extends Thread{
     private final Semaphore mutex;
     private final Puntaje puntaje;
    
    public Incrementador(Semaphore mutex, Puntaje puntaje){
        this.mutex=mutex;
        this.puntaje=puntaje;
         
    }
    
    @Override
    public void run(){
         try {
             mutex.acquire();
             puntaje.incrementarPuntaje();
             System.out.println(puntaje.getPuntaje());
             mutex.release();
         } catch (InterruptedException ex) {
             Logger.getLogger(Incrementador.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
}
