/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.Pregunta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Temporizador t = new Temporizador(lblTemporizador);
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
            bt.setText(p1.getOPCIONES().get(contador));                  
            //Agregar el evento
            bt.setOnAction(e -> {
                if(bt.getText().equals(p1.getOPCIONES().get(p1.getRespuesta()))){
                    System.out.println("RESPUESTA CORRECTA");
                }else{
                    System.out.println("PUTAAAAAAAAAAAAAAA");
                }
            });
            contador++;
        }
    }
    
    
    
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
                System.out.println("Acabo tu turno bitch");
                iniciar = false;
            }
            
        }   
    }
    
}