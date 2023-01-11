package com.mycompany.proyectodiscretas;

import Modelo.Categorias;
import Modelo.Jugador;
import Modelo.Pregunta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {
    public static final String PATHPREGUNTAS="Preguntas/";
    public static final String PATHIMAGES="Images/";
    private static Scene scene;
    public static ArrayList<ArrayList<Pregunta>> preguntas=llenarListaPreguntas();
    public static ArrayList<Jugador> listaJugadores = new ArrayList<>();
//    public static ArrayList<Pregunta> preguntasArte=cargarPreguntas(PATHFILES+"PreguntasArte.txt");
//    public static ArrayList<Pregunta> preguntasHistoria=cargarPreguntas(PATHFILES+"PreguntasCiencia.txt");
//    public static ArrayList<Pregunta> preguntasDeportes=cargarPreguntas(PATHFILES+"PreguntasDeportes.txt");
//    public static ArrayList<Pregunta> preguntasEntretenimiento=cargarPreguntas(PATHFILES+"PreguntasEntretenimiento.txt");
//    public static ArrayList<Pregunta> preguntasCiencia=cargarPreguntas(PATHFILES+"PreguntasHistoria.txt");
//    public static ArrayList<Pregunta> preguntasGeografia=cargarPreguntas(PATHFILES+"PreguntasGeografia.txt");
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 900, 500);
        stage.setScene(scene);
        stage.setTitle("Árbol Discreto");
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    public static ArrayList<Pregunta> cargarPreguntas(String ruta){
        ArrayList<Pregunta> preguntasCargadas=new ArrayList<>();
        try(BufferedReader bf=new BufferedReader(new FileReader(ruta,StandardCharsets.UTF_8))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] datos=linea.split(",");
                ArrayList<String> opciones=new ArrayList<>();
                for(int i=3;i<7;i++){
                    opciones.add(datos[i]);
                }
                Pregunta nueva=new Pregunta(datos[0],datos[1],Categorias.valueOf(datos[2]),opciones,Integer.valueOf(datos[7]));
                preguntasCargadas.add(nueva);
            }
        }catch(FileNotFoundException fe){
            fe.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
        return preguntasCargadas;
    }
    public static ArrayList<ArrayList<Pregunta>> llenarListaPreguntas(){
        ArrayList<ArrayList<Pregunta>> retorno=new ArrayList<>();
        File directorioPreguntas=new File(PATHPREGUNTAS);
        for(File f:directorioPreguntas.listFiles()){
            retorno.add(cargarPreguntas(f.toString()));
        }
        return retorno;
    }
    public static Image abrirImagen(String ruta,double ancho,double alto){
        Image img=null;
        try(FileInputStream fi=new FileInputStream(ruta)){
            img=new Image(fi, ancho, alto, false, true);
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
        return img;
    }
}