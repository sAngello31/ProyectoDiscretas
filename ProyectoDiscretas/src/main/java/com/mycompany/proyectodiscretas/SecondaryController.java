package com.mycompany.proyectodiscretas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SecondaryController {
    
    int jugadores = 2;
    
    @FXML
    private Button btAgregar;
    @FXML
    private VBox vboxGrande;
    @FXML 
    private Button btIniciar;


    
    @FXML
    private void agregarJugador() throws IOException{
        if(jugadores  == 4){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Solo se pueden agregar 4 jugadores maximos");
            a.showAndWait();
        }
        
        else{
            System.out.println("Hola");
            HBox jugador = new HBox(2);
            Label titulo = new Label("Jugador " + (jugadores+1) + ":");
            TextField tfJugador = new TextField();
            
            jugador.getChildren().add(titulo);
            jugador.getChildren().add(tfJugador);
            jugador.setAlignment(Pos.CENTER);
            
            vboxGrande.getChildren().add(jugador);
            jugadores += 1;
        }
    }
    
    @FXML
    private void iniciarJuego() throws IOException{
        
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}