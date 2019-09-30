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
import rmiserver.RmiServerIntf;




public class RmiClient { 
    public static void main(String args[]) throws Exception {
        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
        obj.incrementarPuntaje();
        obj.incrementarPuntaje();
        obj.incrementarPuntaje();
        System.out.println(obj.obtenerPuntaje()); 
    }
}