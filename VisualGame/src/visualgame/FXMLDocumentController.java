/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualgame;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
    
    @FXML private Label score;
    @FXML private TextField oracionAOrdenar;
    @FXML private JFXTextField input;
    @FXML private Label label;
    
    @FXML
    private void skip(ActionEvent event) {
        oracionAOrdenar.setText("Hello World!");
        puntos.decrementarPuntaje();
        score.setText(String.valueOf(puntos.getPuntaje()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        score.setText(String.valueOf(puntos.getPuntaje()));
        input.textProperty().addListener((ObservableValue<? extends String> observableValue, String s, String s2) -> {
            label.setText(observableValue.getValue());
        });
    }    
    
}
