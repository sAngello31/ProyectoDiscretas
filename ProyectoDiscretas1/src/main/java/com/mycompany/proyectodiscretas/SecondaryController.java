package com.mycompany.proyectodiscretas;

import Modelo.Jugador;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {
    
    int jugadores = 2;
    @FXML
    private VBox vboxGrande;
    @FXML 
    private Button btIniciar;
    @FXML
    private TextField jugador1;
    @FXML
    private TextField jugador2;
    @FXML
    ImageView imgvpersonas;

    
    @FXML
    private void iniciarJuego() throws IOException{
        String nombreJ1 = jugador1.getText();
        String nombreJ2 = jugador2.getText();
        
        if(nombreJ1.equals("") || nombreJ2.equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Ingrese el nombre a todos los jugadores");
            a.showAndWait();
        }
        else{
            Jugador j1 = new Jugador(nombreJ1);
            Jugador j2 = new Jugador(nombreJ2);
            
            App.listaJugadores.add(j1);
            App.listaJugadores.add(j2);
            
//            Stage s=(Stage) vboxGrande.getScene().getWindow();
//            Scene scene=new Scene(App.loadFXML("Pregunta"),900,500);
//            s.setScene(scene);
            App.setRoot("Pregunta");
        }
       
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try ( FileInputStream input = new FileInputStream("Popup/" + "personitas.png")) {
            Image image = new Image(input, 173, 147, false, false);
            imgvpersonas.setImage(image);
        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
        }
    }
}