/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Angello Bravo
 */
public class PreguntaMostradaController implements Initializable{
    
    @FXML
    private Label lblJugador;
    @FXML
    private Label lblTemporizador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Temporizador t = new Temporizador(lblTemporizador);
        t.setDaemon(true);
        t.start();
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
