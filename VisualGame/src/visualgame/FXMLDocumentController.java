/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualgame;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.Puntaje;

/**
 *
 * @author Arturo Lessieur
 */
public class FXMLDocumentController implements Initializable {
    
    private final Puntaje puntos = new Puntaje();
    
    private Diccionario diccionario;
    
    private Verb verbo = new Verb();
    private final Random random = new Random();
    private int modo;
    
    @FXML private Label score;
    @FXML private Text verbMeaning;
    @FXML private Text verbName;
    @FXML private JFXTextField input;
    @FXML private Label label;
    
    
    
    @FXML
    private void skip(ActionEvent event) {
        puntos.decrementarPuntaje();
        modo = random.nextInt(2);
        if (modo==0){
            verbo = diccionario.obtenerVerbo();
            verbMeaning.setText(verbo.getMeaning());
            verbName.setText("?");
        }
        else{
            verbo = diccionario.obtenerVerbo();
            verbName.setText(verbo.getName());
            verbMeaning.setText("?");
        }
        score.setText(String.valueOf(puntos.getPuntaje()));
        Platform.runLater(() -> {
            input.clear();
        });
        if (puntos.getPuntaje()==0){
            Alert alert = new Alert(AlertType.CONFIRMATION); 
            alert.setTitle("Game Over");
            alert.setHeaderText("You spent all your points in skips.");
            alert.setContentText("Do you want to try again?"); 
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent()){
               // alert is exited, no button has been pressed.
            }
            else if(result.get() == ButtonType.OK){
                puntos.resetearPuntaje();
                modo = random.nextInt(2);
                if (modo==0){
                    verbo = diccionario.obtenerVerbo();
                    verbMeaning.setText(verbo.getMeaning());
                    verbName.setText("?");
                }
                else{
                    verbo = diccionario.obtenerVerbo();
                    verbName.setText(verbo.getName());
                    verbMeaning.setText("?");
                }
                score.setText(String.valueOf(puntos.getPuntaje()));
                Platform.runLater(() -> {
                    input.clear();
                });
            }
            else if(result.get() == ButtonType.CANCEL){
                Platform.exit();   
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diccionario= new Diccionario();
        modo = random.nextInt(2);
        if (modo==0){
            verbo = diccionario.obtenerVerbo();
            verbMeaning.setText(verbo.getMeaning());
            verbName.setText("?");
        }
        else{
            verbo = diccionario.obtenerVerbo();
            verbName.setText(verbo.getName());
            verbMeaning.setText("?");
        }
        score.setText(String.valueOf(puntos.getPuntaje()));
        input.textProperty().addListener((ObservableValue<? extends String> observableValue, String s, String s2) -> {
            if (modo==0){
                if (observableValue.getValue().equals(verbo.getName())){
                    puntos.incrementarPuntaje();
                    modo=random.nextInt(2);
                    if (modo==0){
                        verbo = diccionario.obtenerVerbo();
                        verbMeaning.setText(verbo.getMeaning());
                        verbName.setText("?");
                    }
                    else{
                        verbo = diccionario.obtenerVerbo();
                        verbName.setText(verbo.getName());
                        verbMeaning.setText("?");
                    }
                    score.setText(String.valueOf(puntos.getPuntaje()));
                    Platform.runLater(() -> {
                        input.clear();
                    });
                }
            }
            else{
                if (observableValue.getValue().equals(verbo.getMeaning())){
                    puntos.incrementarPuntaje();
                    modo=random.nextInt(2);
                    if (modo==0){
                        verbo = diccionario.obtenerVerbo();
                        verbMeaning.setText(verbo.getMeaning());
                        verbName.setText("?");
                    }
                    else{
                        verbo = diccionario.obtenerVerbo();
                        verbName.setText(verbo.getName());
                        verbMeaning.setText("?");
                    }
                    score.setText(String.valueOf(puntos.getPuntaje()));
                    Platform.runLater(() -> {
                        input.clear();
                    });
                }
            }
            
            
        });
    }    
    
}
