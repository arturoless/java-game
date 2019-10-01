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
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
//import rmiserver.RmiServerIntf;




public class RmiClient { 
    public static void main(String args[]) throws Exception {
        LocateRegistry.getRegistry(1099); 
        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//192.168.0.27/RmiServer");
        
        obj.incrementarPuntaje();
        obj.incrementarPuntaje();
        obj.incrementarPuntaje();
        System.out.println(obj.obtenerPuntaje()); 
    }
}