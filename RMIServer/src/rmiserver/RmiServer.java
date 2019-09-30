/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

/**
 *
 * @author Arturo Lessieur
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
      
    Semaphore mutex = new Semaphore(1);
    Puntaje puntaje = new Puntaje();
    Incrementador incrementador = new Incrementador(mutex,puntaje);
    Decrementador decrementador = new Decrementador(mutex,puntaje);
    
    public RmiServer() throws RemoteException {
        super(0);    // required to avoid the 'rmic' step, see below
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

    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
           
        //Instantiate RmiServer

        RmiServer obj = new RmiServer();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/RmiServer", obj);
        System.out.println("PeerServer bound in registry");
    }
}
