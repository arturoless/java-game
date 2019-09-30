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
import rmiclient.*;
import java.rmi.Naming;

public class RmiClient { 
    public static void main(String args[]) throws Exception {
        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
        obj.incrementarPuntaje();
        obj.incrementarPuntaje();
        obj.incrementarPuntaje();
        System.out.println(obj.obtenerPuntaje());    }
}