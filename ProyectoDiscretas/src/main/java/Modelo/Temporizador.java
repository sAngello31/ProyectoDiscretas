/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Angello Bravo
 */
public class Temporizador extends Thread{
   boolean iniciar = true;
   int segundos = 15;
   @FXML
   Label e;
   Temporizador(Label e){
       this.e = e;
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
            System.out.println("Error");
        }
    }
   
   public void ejecutarTemporizador(){
       segundos--;
       Platform.runLater(() -> e.setText(""+segundos));
   }
   
}
