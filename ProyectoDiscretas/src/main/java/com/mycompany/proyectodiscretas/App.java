package com.mycompany.proyectodiscretas;

import Modelo.Categorias;
import Modelo.Jugador;
import Modelo.Pregunta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    public static ArrayList<ArrayList<Pregunta>> preguntas=new ArrayList<>();
    public static ArrayList<Jugador> listaJugadores = new ArrayList<>();
    public static ArrayList<Pregunta> preguntasArte= new ArrayList<>();
    public static ArrayList<Pregunta> preguntasHistoria= new ArrayList<>();
    public static ArrayList<Pregunta> preguntasDeportes= new ArrayList<>();
    public static ArrayList<Pregunta> preguntasEntretenimiento= new ArrayList<>();
    public static ArrayList<Pregunta> preguntasCiencia= new ArrayList<>();
    public static ArrayList<Pregunta> preguntasGeografia= new ArrayList<>();
    
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<String> opciones=new ArrayList<>();
        opciones.add("2");
        opciones.add("3");
        opciones.add("1");
        opciones.add("0");
        
        PreguntaRealizadaController.pregunta=new Pregunta("1","¿Cuánto es 1+1?",Categorias.Ciencia,opciones,0);
        scene = new Scene(loadFXML("PreguntaRealizada"), 900, 500);

        stage.setScene(scene);
        stage.setTitle("Árbol Discreto");
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}