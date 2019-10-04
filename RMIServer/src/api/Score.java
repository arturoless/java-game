/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.rmi.*;
import rmiserver.Diccionario;

/**
 *
 * @author Johan Del Valle
 */
public interface Score extends Remote {

    public void compararPuntaje(int puntos) throws RemoteException;

    public int obtenerPuntaje() throws RemoteException;
    
    public Diccionario obtenerDiccionario() throws RemoteException;
}
