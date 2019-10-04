/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Arturo Lessieur
 */
public class Diccionario implements Serializable{
    private static final long serialVersionUID = 1190476516911661470L;
    private ArrayList<Verb> verbs = new ArrayList<Verb>();
    private Random random = new Random();
    
    public Diccionario(){
        
        try
        {
          String myDriver = "com.mysql.cj.jdbc.Driver";
          String myUrl = "jdbc:mysql://localhost/diccionario?serverTimezone=UTC&useSSL=false";
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, "root", "root");

          String query = "SELECT * FROM verbs";

          Statement st = conn.createStatement();

          ResultSet rs = st.executeQuery(query);
          while (rs.next())
          {
            Verb verb = new Verb();
            String name = rs.getString("verb");
            verb.setName(name);
            String meaning = rs.getString("meaning");
            verb.setMeaning(meaning);
            verbs.add(verb);
          }
          st.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
     
    }
    
    public Verb obtenerVerbo(){
        int index;
        Verb verb;
        if(!verbs.isEmpty()){
            index = random.nextInt(verbs.size());
            verb = verbs.get(index);
            return verb;
        }
        else{
            verb = new Verb();
        }
        return verb;
    }
    
    public void removerVerbo(Verb verb){
        verbs.remove(verbs.indexOf(verb));
    }
    
     public Verb cambiarVerbo(Verb verb){
        removerVerbo(verb);
        return obtenerVerbo();
    }
    
    public void showAnswer() {
        
    }
    
    
    
}
