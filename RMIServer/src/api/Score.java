/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.rmi.*;

/**
 *
 * @author Johan Del Valle
 */
public interface Score extends Remote {
    public void incrementarPuntaje() throws RemoteException;

    public void decrementarPuntaje() throws RemoteException;

    public int obtenerPuntaje() throws RemoteException;
}
