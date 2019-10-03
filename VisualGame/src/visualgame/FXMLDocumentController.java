/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualgame;

import api.Score;
import rmiserver.Diccionario;
import rmiserver.Verb;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
    private Timeline timeline;
    @FXML private Label score;
    @FXML private Text verbMeaning;
    @FXML private Text verbName;
    @FXML private JFXTextField input;
    @FXML private Label label;
    @FXML private ProgressBar time ;
    
    private void gameOver(){
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
                temporizador();
                Platform.runLater(() -> {
                    input.clear();
                });
            }  
            else if(result.get() == ButtonType.CANCEL){
                Platform.exit();   
            }
    }
    
    private void temp(){
        
    }
     private void temporizador(){
        IntegerProperty seconds = new SimpleIntegerProperty();
        time.progressProperty().bind(seconds.divide(60.0));
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(seconds,0)),
                new KeyFrame(Duration.minutes(1), e ->{}, new KeyValue(seconds, 60))     
        );
        System.out.println( timeline.getCuePoints());
        timeline.setOnFinished((ActionEvent e)->{
            try {
                Platform.runLater(()->{
                    gameOver();
                });
            } catch (Exception ex) {
                System.out.println(ex);
            }
            
        });
        timeline.playFromStart();
        
    }
    
    private void obtenerDiccionario() throws RemoteException, NotBoundException{
        
        Registry registry;
        registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
        Score jaja = (Score) registry.lookup("verbs");
        diccionario = jaja.obtenerDiccionario();
            
    }
    
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
            timeline.pause();
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
                temporizador();
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
        temporizador();
        try {
            obtenerDiccionario();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        diccionario = new Diccionario();
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
