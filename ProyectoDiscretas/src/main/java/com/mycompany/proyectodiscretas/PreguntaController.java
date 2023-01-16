/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.ArbolBinario;
import Modelo.Jugador;
import Modelo.Pregunta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PreguntaController implements Initializable {

    @FXML
    private VBox vbRoot;
    @FXML
    private Button btn1;
    @FXML
    public static Pane paneArbol1;
    @FXML
    public static Pane paneArbol2;
    @FXML
    private HBox hbB2;
    @FXML
    private HBox hbB3;
    @FXML
    private HBox hbB4;
    @FXML
    private Label lblPlayer;
    @FXML
    private Label lblTiempo;
    @FXML
    private Pane arbol;
    @FXML
    private ArrayList<Button> botones;
    private Jugador jugadorActual;
    public static ArbolBinario<Pregunta> arbolJugador1 = crearArbol();
    public static ArbolBinario<Pregunta> arbolJugador2 = crearArbol();
    
    
    public static int turno = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(!(turno%2 ==0)){
            lblPlayer.setText("Jugador 1: " + App.listaJugadores.get(0).getUsername());
        }
        
        else{
            lblPlayer.setText("Jugador 2: " + App.listaJugadores.get(1).getUsername());
        }
        
        llenarListaBotones();
        cargarImagenesBotones();
        añadirEventHandlerBtn();
    }

    private void llenarListaBotones() {
        ArrayList<Button> btns = new ArrayList<>();
        ArrayList<HBox> hboxes = new ArrayList<>();
        hboxes.add(hbB2);
        hboxes.add(hbB3);
        hboxes.add(hbB4);
        for (HBox h : hboxes) {
            for (Node n : h.getChildren()) {
                Button btn = (Button) n;
                btns.add(btn);
            }
        }
        botones = btns;
    }

    private static ArbolBinario<Pregunta> crearArbol() {
        ArbolBinario<Pregunta> arbol = new ArbolBinario((p1, p2) -> {
            return 0;
        },Pregunta.class);
        Collections.shuffle(App.preguntas);
        for (ArrayList<Pregunta> arrP : App.preguntas) {
            Collections.shuffle(arrP);
        }
        if (!App.preguntas.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                for (ArrayList<Pregunta> arrP : App.preguntas) {
                    arbol.encolar(arrP.get(i));
                }
                arbol.encolar(App.preguntas.get(i).get(2));
            }
        }
        return arbol;
    }
    private void cargarImagenesBotones(){
        
        ArbolBinario<Pregunta> ab;
        
        if(!(turno %2 == 0)){
            ab = arbolJugador1;
            
        }
        
        else{
            ab = arbolJugador2;
        }
        
        for(int i=0;i<14;i++){
            Image img1=App.abrirImagen(App.PATHIMAGES+ab.arr[i].getCategoria().name()+".png",50,47.32);
            BackgroundImage bgImg1=new BackgroundImage(img1,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
            Background bg=new Background(bgImg1);
            botones.get(i).setStyle("");
            botones.get(i).setBackground(bg);
        }
    }

    private void añadirEventHandlerBtn() {
        for (int i = 0; i < botones.size(); i++) {
            Pregunta p = arbolJugador1.arr[i];
            Pregunta p1 = arbolJugador2.arr[i];
            botones.get(i).setOnAction(e -> {
                try{
                escogerPregunta(p, p1);
                }
                
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            });
        }
    }

//    private Pane mostrarPregunta(Pregunta p, Pregunta p2) {
//        Pane paneArbol = (Pane) vbRoot.getChildren().remove(vbRoot.getChildren().size() - 1);
//        VBox root = new VBox();
//        HBox hbEnunciado = new HBox();
//        hbEnunciado.getChildren().add(new Label(p.getEnunciado()));
//        hbEnunciado.setAlignment(Pos.CENTER);
//        root.getChildren().add(hbEnunciado);
//        ToggleGroup tg = new ToggleGroup();
//        Pregunta preguntaAMostrar;
//        if (turno % 2 == 0) {
//            preguntaAMostrar=p2;
//        } else {
//            preguntaAMostrar=p;
//        }
//        for (String op : preguntaAMostrar.getOPCIONES()) {
//            HBox hbOp = new HBox();
//            RadioButton rb = new RadioButton();
//            tg.getToggles().add(rb);
//            hbOp.getChildren().addAll(rb, new Label(op));
//            hbOp.setPadding(new Insets(0, 0, 0, 10));
//            hbOp.setSpacing(15);
//            root.getChildren().add(hbOp);
//        }
//        HBox hbBtnEnviar = new HBox();
//        Button btnEnviar = new Button("Enviar");
//        btnEnviar.setOnAction(e -> {
//            verificarRespuesta(preguntaAMostrar, tg);
//        });
//        hbBtnEnviar.getChildren().add(btnEnviar);
//        root.getChildren().add(hbBtnEnviar);
//        vbRoot.getChildren().add(root);
//        return paneArbol;
//    }

//    private void verificarRespuesta(Pregunta pregunta, ToggleGroup tg) {
//        RadioButton correcto = (RadioButton) tg.getToggles().get(pregunta.getRespuesta());
//        if (correcto.isSelected()) {
//            System.out.println("Correcto");
//        } else {
//            System.out.println("Jaja, loquitop");
//        }
//    }
    
    private Pane obtenerArbolJugador(){
        Pane p = (Pane) vbRoot.getChildren().remove(vbRoot.getChildren().size() - 1);
        return p;
    }
    
    @FXML
    private void escogerPregunta(Pregunta p1, Pregunta p2) throws IOException {
        
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("PreguntaMostrada.fxml"));
        PreguntaMostradaController ct = new PreguntaMostradaController();
        fxml.setController(ct);
        VBox root = (VBox)fxml.load();
        
        ct.llenarDatos(p1, p2);
        ct.mostrarPreguntas(p1,  p2);
        App.changeRoot(root);
    }
}
