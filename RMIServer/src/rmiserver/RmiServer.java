/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Arturo Lessieur
 */
public class RmiServer extends UnicastRemoteObject implements RmiServerIntf{
    Semaphore mutex = new Semaphore(1);
    Puntaje puntaje = new Puntaje();
    Incrementador incrementador = new Incrementador(mutex,puntaje);
    Decrementador decrementador = new Decrementador(mutex,puntaje);
    
    public RmiServer() throws RemoteException {
        super(0);
    }
    
  
    @Override
    public void incrementarPuntaje(){
        incrementador.run();
        try {
            incrementador.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(RmiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        incrementador.interrupt();
    }
    

    @Override
    public void decrementarPuntaje(){
        decrementador.run();
        try {
            incrementador.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(RmiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        decrementador.interrupt();
    }
    
    @Override
    public int obtenerPuntaje(){
        return puntaje.getPuntaje();
    }
}
