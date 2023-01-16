/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.Pregunta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Angello Bravo
 */
public class PreguntaMostradaController implements Initializable{
    
    @FXML
    private Label lblJugador;
    @FXML
    private Label lblTemporizador;
    @FXML
    private Label lblPregunta;
    @FXML
    private GridPane Angello;
    
    Temporizador t;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t = new Temporizador(lblTemporizador);
        t.setDaemon(true);
        t.start();
    }
    
    
    public void llenarDatos(Pregunta p1, Pregunta p2){
        if(!(PreguntaController.turno%2 == 0)){
            lblJugador.setText(App.listaJugadores.get(0).getUsername());
            lblPregunta.setText(p1.getEnunciado());
        }
        
        else{
            lblJugador.setText(App.listaJugadores.get(1).getUsername());
            lblPregunta.setText(p2.getEnunciado());
        }
    }
    
    public void mostrarPreguntas(Pregunta p1, Pregunta p2) {        
//        Pregunta preguntaAMostrar;
//        if (turno % 2 == 0) {
//            preguntaAMostrar=p2;
//        } else {
//            preguntaAMostrar=p1;
//        }
//        
        int contador=0;
        for(Node node: Angello.getChildren()){
            Button bt = (Button)node;
            if(!(PreguntaController.turno%2 ==0)){
                bt.setText(p1.getOPCIONES().get(contador));  
            }
            
            else{
                bt.setText(p2.getOPCIONES().get(contador));  
            }
                            
            //Agregar el evento
            bt.setOnAction(e -> verificarRespuesta(p1, bt));
            contador++;
        }
    }
    
    private void verificarRespuesta(Pregunta p, Button bt){
        if(bt.getText().equals(p.getOPCIONES().get(p.getRespuesta()))){
            t.setIniciar(false);
            bt.setStyle("-fx-background-color: green; -fx-text-fill: white");
            Alert correcto=new Alert(Alert.AlertType.ERROR);
            correcto.setHeaderText("FELICIDADES ACERTASTE");
            correcto.setTitle("RESPUESTA CORRECTA");
            correcto.showAndWait();
            System.out.println("RESPUESTA CORRECTA");
            //Cambio de Ventana y Turno 
            PreguntaController.turno++;
            try{
                cambiarScene();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
            
        }
        
        else{
            t.setIniciar(false);
            bt.setStyle("-fx-background-color: red; -fx-text-fill: white");
            Alert incorrecto=new Alert(Alert.AlertType.ERROR);
            incorrecto.setHeaderText("VALISTE");
            incorrecto.setTitle("RESPUESTA INCORRECTA");
            incorrecto.showAndWait();
            System.out.println("PUTAAAAAAAAAAAAAAA");
                    
            //Cambio de Ventana y Turno
            PreguntaController.turno++;
            try{
                cambiarScene();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void temporizadorFinalizado(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("SE TE ACABÓ EL TIEMPO");
        a.showAndWait();
        try{
            cambiarScene();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void cambiarScene() throws IOException{
        App.setRoot("Pregunta");
    }
    
    //Hilo
    private class Temporizador extends Thread{
        boolean iniciar = true;
        int segundos = 15;
        
        @FXML
        Label e;
        Temporizador(Label e){
            this.e = e;
        }

        public void setIniciar(boolean iniciar) {
            this.iniciar = iniciar;
        }
        

        @Override
        public void run() {
            try{
                while(iniciar){
                    ejecutarTemporizador();
                    Thread.sleep(1000);
                }   
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
        public void ejecutarTemporizador(){
            segundos--;
            if(segundos != 0){
                Platform.runLater(() -> e.setText("Tiempo: "+segundos));
                System.out.println(segundos);
            }
            else{
                PreguntaController.turno++;
                iniciar = false;
                Platform.runLater(() -> temporizadorFinalizado());
            }
            
        }
        
    }
    
}
