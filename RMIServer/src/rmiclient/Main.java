/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclient;

/**
 *
 * @author Arturo Lessieur
 */
import DiccionarioIntf.Score;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;





public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("192.168.43.99", Registry.REGISTRY_PORT);
        System.out.println(registry.toString());
        Score score = (Score) registry.lookup("verbs");
        score.incrementarPuntaje();
        
        DiccionarioIntf.Diccionario diccionario = score.obtenerDiccionario();
        
        System.out.println(diccionario.obtenerVerbo().getName());
        System.out.println(score.obtenerPuntaje());
    }
}