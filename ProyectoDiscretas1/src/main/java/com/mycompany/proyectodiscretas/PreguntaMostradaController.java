/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.Jugador;
import Modelo.Pregunta;
import Modelo.Temporizador;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Angello Bravo
 */
public class PreguntaMostradaController implements Initializable {

    @FXML
    private Label lblJugador;
    @FXML
    public Label lblTemporizador;
    @FXML
    private Label lblPregunta;
    @FXML
    private GridPane Angello;
    public Temporizador tempo;

    public static VBox j1;
    public static VBox j2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tempo.e = lblTemporizador;
        tempo.setDaemon(true);
        tempo.start();
    }

    public void llenarDatos(Pregunta p) {
        if (!(App.turno % 2 == 0)) {
            lblJugador.setText(App.listaJugadores.get(0).getUsername());

        } else {
            lblJugador.setText(App.listaJugadores.get(1).getUsername());
        }
        lblPregunta.setText(p.getEnunciado());
    }

    public void mostrarPreguntas(Pregunta p, VBox root) {
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
            bt.setOnAction(e -> verificarRespuesta(p, bt, root));
            contador++;
        }
    }

    private void verificarRespuesta(Pregunta p, Button bt, VBox root) {
        boolean correcto1;
        if (bt.getText().equals(p.getOPCIONES().get(p.getRespuesta()))) {
            correcto1 = true;
            tempo.setIniciar(false);
            bt.setStyle("-fx-background-color: green; -fx-text-fill: white");
//            Alert correcto = new Alert(Alert.AlertType.ERROR);
//            correcto.setHeaderText("FELICIDADES ACERTASTE");
//            correcto.setTitle("RESPUESTA CORRECTA");
//            correcto.showAndWait();
//            System.out.println("RESPUESTA CORRECTA");
            VBox popup = new VBox();
            Label respuesta = new Label("RESPUESTA CORRECTA");
            ImageView imgv = new ImageView();
            Label segundos = new Label();
            try ( FileInputStream input = new FileInputStream("Popup/" + "acertaste.gif")) {
                Image image = new Image(input, 200, 150, false, false);
                imgv.setImage(image);
            } catch (IOException e) {
                System.out.println("Archivo no encontrado");
            }

            respuesta.setStyle("-fx-font-weight: bold; -fx-font-size: 29");
//            respuesta.setStyle("-fx-font-size: 29");   
            popup.setSpacing(20);
            popup.getChildren().addAll(respuesta, imgv, segundos);
            popup.setStyle("-fx-background-color: #99ff33");
            popup.setAlignment(Pos.CENTER);
            segundos.setStyle("-fx-font-size: 14");

            Scene scene2 = new Scene(popup, 360, 294);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();

            Thread t2 = new Thread(new Runnable() {
                int i = 6;

                @Override
                public void run() {
                    while (i >= 0) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                segundos.setText("Se cierra en " + i + " segundos");
                                if (i == 0) {
                                    stage2.close();
                                    AngelloyMargarita(root,p,correcto1);
                                } else {
                                    
                                }
                            }
                        });
                        i--;

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            t2.setDaemon(true);
            t2.start();

            //Cambio de Ventana y Turno 
            App.turno++;

//            try {
//                cambiarScene();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
        } else {
            correcto1 = false;
            tempo.setIniciar(false);
            bt.setStyle("-fx-background-color: red; -fx-text-fill: white");
//            Alert incorrecto = new Alert(Alert.AlertType.ERROR);
//            incorrecto.setHeaderText("VALISTE");
//            incorrecto.setTitle("RESPUESTA INCORRECTA");
//            incorrecto.showAndWait();
//            System.out.println("PUTAAAAAAAAAAAAAAA");

            VBox popup = new VBox();
            Label respuesta = new Label("RESPUESTA INCORRECTA");
            ImageView imgv = new ImageView();
            Label segundos = new Label();
            try ( FileInputStream input = new FileInputStream("Popup/" + "perdiste.gif")) {
                Image image = new Image(input, 200, 150, false, false);
                imgv.setImage(image);
            } catch (IOException e) {
                System.out.println("Archivo no encontrado");
            }

            respuesta.setStyle("-fx-font-weight: bold; -fx-font-size: 29");
//            respuesta.setStyle("-fx-font-size: 29");
            popup.setSpacing(20);
            popup.getChildren().addAll(respuesta, imgv, segundos);
            popup.setStyle("-fx-background-color: #f3464a");
            popup.setAlignment(Pos.CENTER);
            segundos.setStyle("-fx-font-size: 14");

            Scene scene2 = new Scene(popup, 360, 294);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();

            Thread t2 = new Thread(new Runnable() {
                int i = 6;

                @Override
                public void run() {
                    while (i >= 0) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                segundos.setText("Se cierra en " + i + " segundos");
                                if (i == 0) {
                                    stage2.close();
                                    AngelloyMargarita(root,p,correcto1);
                                } else {

                                }
                            }
                        });
                        i--;

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            t2.setDaemon(true);
            t2.start();

            //Cambio de Ventana y Turno
            App.turno++;
//            try{
//                cambiarScene();
//            }
//            catch(IOException e){
//                System.out.println(e.getMessage());
//            }  
        }

    }
    public void AngelloyMargarita(VBox root,Pregunta p,boolean correcto1){
                if (App.turno == 2) {
            j1 = root;
            int indicePregunta =PreguntaController.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController.bloquearBotones();
            PreguntaController.borrarEventHandler(indicePregunta);
            try {
                cambiarScene2();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (App.turno == 3) {
            j2 = root;
            int indicePregunta =PreguntaController1.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController1.bloquearBotones();
            PreguntaController1.borrarEventHandler(indicePregunta);
            App.changeRoot(j1);
        } else if (App.turno % 2 == 1) {
            j2 = root;
            int indicePregunta = PreguntaController1.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController1.borrarEventHandler(indicePregunta);
            if (indicePregunta >= 6 && correcto1) {
                mostrarGanador(App.listaJugadores.get(1));
            } else {
                boolean todosBloqueados=PreguntaController1.bloquearBotones();
                if(todosBloqueados){
                    mostrarGanador(App.listaJugadores.get(0));
                }else{
                    App.changeRoot(j1);
                }
            }
        } else if (App.turno % 2 == 0) {
            j1 = root;
            int indicePregunta = PreguntaController.cambiarEstadoPreguntas(p, correcto1);
            PreguntaController.borrarEventHandler(indicePregunta);
            if (indicePregunta >= 6 && correcto1) {
                mostrarGanador(App.listaJugadores.get(0));
            } else {
                boolean todosBloqueados=PreguntaController.bloquearBotones();
                if(todosBloqueados){
                    mostrarGanador(App.listaJugadores.get(1));
                }else{
                    App.changeRoot(j2);
                }
            }
        }
    }
    public void mostrarGanador(Jugador j) {
        GanadorController.ganador = j;
        try {
            App.setRoot("Ganador");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//    public void temporizadorFinalizado(Temporizador t) {
//        VBox root;
//        Alert a = new Alert(Alert.AlertType.ERROR);
//        a.setHeaderText("SE TE ACABÓ EL TIEMPO");
//        a.showAndWait();
//                if (App.turno == 2) {
//            j1=root;
//            PreguntaController.cambiarEstadoPreguntas(p, correcto1);
//            PreguntaController.bloquearBotones();
//            try {
//                cambiarScene2();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }else if(App.turno == 3){
//            j2=root;
//            PreguntaController1.cambiarEstadoPreguntas(p, correcto1);
//            PreguntaController1.bloquearBotones();
//            App.changeRoot(j1);
//        }else if(App.turno%2==1){
//            j2=root;
//            int indicePregunta=PreguntaController1.cambiarEstadoPreguntas(p, correcto1);
//            if(indicePregunta>=6&&correcto1){
//                mostrarGanador(App.listaJugadores.get(1));
//            }else{
//                PreguntaController1.bloquearBotones();
//                App.changeRoot(j1);
//            }
//        }else if(App.turno%2==0){
//            j1=root;
//            int indicePregunta=PreguntaController.cambiarEstadoPreguntas(p, correcto1);
//            if(indicePregunta>=6&&correcto1){
//                mostrarGanador(App.listaJugadores.get(0));
//            }else{
//                PreguntaController.bloquearBotones();
//                App.changeRoot(j2);
//            }
//        }
//        try{
//            cambiarScene();
//        }
//        catch(IOException e){
//            System.out.println(e.getMessage());
//        }
//    private void cambiarScene() throws IOException{
//        App.setRoot("Pregunta");
//    }
//    

    private void cambiarScene2() throws IOException {
        App.setRoot("Pregunta_1");
    }

    //Hilo
//    private class Temporizador extends Thread {
//
//        boolean iniciar = true;
//        int segundos = 15;
//        VBox root;
//        @FXML
//        Label e;
//
//        Temporizador(VBox e) {
//            this.root = e;
//        }
//
//        public void setIniciar(boolean iniciar) {
//            this.iniciar = iniciar;
//        }
//
//        @Override
//        public void run() {
//            try {
//                while (iniciar) {
//                    ejecutarTemporizador();
//                    Thread.sleep(1000);
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        public void ejecutarTemporizador() {
//            segundos--;
//            if (segundos != 0) {
//                Platform.runLater(() -> e.setText("Tiempo: " + segundos));
//                System.out.println(segundos);
//            } else {
//                App.turno++;
//                iniciar = false;
//                Platform.runLater(() -> temporizadorFinalizado(this));
//            }
//
//        }
//
//    }
}
