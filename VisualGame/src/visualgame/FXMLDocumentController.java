/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualgame;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Puntaje;

/**
 *
 * @author Arturo Lessieur
 */
public class FXMLDocumentController implements Initializable {
    
    private Puntaje puntos = new Puntaje();
    
    @FXML
    private Label score;
    @FXML
    private TextField oracionAOrdenar;
    
    @FXML
    private void skip(ActionEvent event) {
        System.out.println("You clicked me!");
        oracionAOrdenar.setText("Hello World!");
        puntos.decrementarPuntaje();
        score.setText(String.valueOf(puntos.getPuntaje()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        score.setText(String.valueOf(puntos.getPuntaje()));
    }    
    
}
