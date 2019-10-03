/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import api.Score;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.remote.rmi.RMIServer;
/**
 *
 * @author Arturo Lessieur
 */
public class Server implements Score {
    Semaphore mutex = new Semaphore(1);
    Puntaje puntaje = new Puntaje();
    Incrementador incrementador = new Incrementador(mutex, puntaje);
    Decrementador decrementador = new Decrementador(mutex, puntaje);

    public Server() throws RemoteException {
        super();
    }

    @Override
    public void incrementarPuntaje() throws RemoteException {
        incrementador.run();
        try {
            incrementador.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        incrementador.interrupt();
    }

    @Override
    public void decrementarPuntaje() throws RemoteException {
        decrementador.run();
        try {
            incrementador.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        decrementador.interrupt();
    }

    @Override
    public int obtenerPuntaje() throws RemoteException {
        return puntaje.getPuntaje();
    }
}
