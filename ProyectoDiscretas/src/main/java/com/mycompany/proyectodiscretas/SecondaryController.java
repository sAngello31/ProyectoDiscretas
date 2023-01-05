package com.mycompany.proyectodiscretas;

import Modelo.Jugador;
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
    private TextField jugador1;
    @FXML
    private TextField jugador2;
    @FXML
    private TextField jugador3;
    @FXML
    private TextField jugador4;

    
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
        }
       
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}