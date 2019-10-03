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
import api.Score;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 

public class Main {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

        Server server = new Server();
        Score score = (Score) UnicastRemoteObject.exportObject(server, 0);

        registry.rebind("verbs", score);
        System.out.println("Server is running in PORT: " + Registry.REGISTRY_PORT);
    }
}
