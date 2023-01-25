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
import javafx.scene.layout.VBox;

/**
 *
 * @author Angello Bravo
 */
public class PreguntaMostradaController implements Initializable {

    @FXML
    private Label lblJugador;
    @FXML
    private Label lblTemporizador;
    @FXML
    private Label lblPregunta;
    @FXML
    private GridPane Angello;
    
    Temporizador t;
    
    static VBox j1;
    static VBox j2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t = new Temporizador(lblTemporizador);
        t.setDaemon(true);
        t.start();
    }

    public void llenarDatos(Pregunta p) {
        if (!(App.turno % 2 == 0)) {
            lblJugador.setText(App.listaJugadores.get(0).getUsername());

        } else {
            lblJugador.setText(App.listaJugadores.get(1).getUsername());
        }
        lblPregunta.setText(p.getEnunciado());
    }

    public void mostrarPreguntas(Pregunta p,VBox root) {
//        Pregunta preguntaAMostrar;
//        if (turno % 2 == 0) {
//            preguntaAMostrar=p2;
//        } else {
//            preguntaAMostrar=p1;
//        }
//        
        int contador = 0;
        for (Node node : Angello.getChildren()) {
            Button bt = (Button) node;
            bt.setText(p.getOPCIONES().get(contador));
            //Agregar el evento
            bt.setOnAction(e -> verificarRespuesta(p, bt,root));
            contador++;
        }
    }

    private void verificarRespuesta(Pregunta p, Button bt,VBox root) {
        boolean correcto1;
        if (bt.getText().equals(p.getOPCIONES().get(p.getRespuesta()))) {
            correcto1=true;
            t.setIniciar(false);
            bt.setStyle("-fx-background-color: green; -fx-text-fill: white");
            Alert correcto = new Alert(Alert.AlertType.ERROR);
            correcto.setHeaderText("FELICIDADES ACERTASTE");
            correcto.setTitle("RESPUESTA CORRECTA");
            correcto.showAndWait();
            System.out.println("RESPUESTA CORRECTA");
            //Cambio de Ventana y Turno 
            App.turno++;

//            try {
//                cambiarScene();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }

        } else {
            correcto1=false;
            t.setIniciar(false);
            bt.setStyle("-fx-background-color: red; -fx-text-fill: white");
            Alert incorrecto = new Alert(Alert.AlertType.ERROR);
            incorrecto.setHeaderText("VALISTE");
            incorrecto.setTitle("RESPUESTA INCORRECTA");
            incorrecto.showAndWait();
            System.out.println("PUTAAAAAAAAAAAAAAA");

            //Cambio de Ventana y Turno
            App.turno++;
//            try{
//                cambiarScene();
//            }
//            catch(IOException e){
//                System.out.println(e.getMessage());
//            }  
        }
        if (App.turno == 2) {
            j1=root;
            PreguntaController.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController.bloquearBotones();
            try {
                cambiarScene2();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }else if(App.turno == 3){
            j2=root;
            PreguntaController1.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController1.bloquearBotones();
            App.changeRoot(j1);
        }else if(App.turno%2==1){
            j2=root;
            PreguntaController1.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController1.bloquearBotones();
            App.changeRoot(j1);
        }else if(App.turno%2==0){
            j1=root;
            PreguntaController.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController.bloquearBotones();
            App.changeRoot(j2);
        }
    }

    public void temporizadorFinalizado() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("SE TE ACABÓ EL TIEMPO");
        a.showAndWait();
//        try{
//            cambiarScene();
//        }
//        catch(IOException e){
//            System.out.println(e.getMessage());
//        }
    }

//    private void cambiarScene() throws IOException{
//        App.setRoot("Pregunta");
//    }
//    
    private void cambiarScene2() throws IOException {
        App.setRoot("Pregunta_1");
    }

    //Hilo
    private class Temporizador extends Thread {

        boolean iniciar = true;
        int segundos = 15;

        @FXML
        Label e;

        Temporizador(Label e) {
            this.e = e;
        }

        public void setIniciar(boolean iniciar) {
            this.iniciar = iniciar;
        }

        @Override
        public void run() {
            try {
                while (iniciar) {
                    ejecutarTemporizador();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void ejecutarTemporizador() {
            segundos--;
            if (segundos != 0) {
                Platform.runLater(() -> e.setText("Tiempo: " + segundos));
                System.out.println(segundos);
            } else {
                App.turno++;
                iniciar = false;
                Platform.runLater(() -> temporizadorFinalizado());
            }

        }

    }

}
