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
    private static final long serialVersionUID = 1L;
    Semaphore mutex = new Semaphore(1);
    Puntaje puntaje = new Puntaje();
   
    
    Diccionario diccionario;

    public Server() throws RemoteException {
        super();
        diccionario = new Diccionario();
    }
    
    
    
    @Override
    public Diccionario obtenerDiccionario(){
        return diccionario;
    }

    @Override
    public void compararPuntaje(int puntos) throws RemoteException {
        Comparador comparador = new Comparador(mutex, puntaje, puntos);
        comparador.run();
        try {
            comparador.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public int obtenerPuntaje() throws RemoteException {
        return puntaje.getPuntaje();
    }
}
