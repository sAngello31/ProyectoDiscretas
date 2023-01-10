/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.ArbolBinario;
import Modelo.Pregunta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    private ArrayList<Button> botones;
    private ArbolBinario<Pregunta> arbolJugador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarListaBotones();
        arbolJugador=crearArbol();
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

    private ArbolBinario<Pregunta> crearArbol() {
        ArbolBinario<Pregunta> arbol = new ArbolBinario((p1, p2) -> {
            return 0;
        });
        Collections.shuffle(App.preguntas);
        for (ArrayList<Pregunta> arrP : App.preguntas) {
            Collections.shuffle(arrP);
        }
        if(!App.preguntas.isEmpty()){
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
        for(int i=0;i<14;i++){
            botones.get(i).setStyle( "-fx-background-image: "+arbolJugador.arr[i].getCategoria().name()+".jpg");
        }
    }

    private void añadirEventHandlerBtn() {
        for(int i=0;i<botones.size();i++){
            Pregunta p=arbolJugador.arr[i];
            botones.get(i).setOnAction(e->{
                mostrarPregunta(p);
            });
        }
    }
    private Pane mostrarPregunta(Pregunta p){
        Pane paneArbol=(Pane)vbRoot.getChildren().remove(vbRoot.getChildren().size()-1);
        VBox root=new VBox();
        HBox hbEnunciado=new HBox();
        hbEnunciado.getChildren().add(new Label(p.getEnunciado()));
        hbEnunciado.setAlignment(Pos.CENTER);
        root.getChildren().add(hbEnunciado);
        ToggleGroup tg=new ToggleGroup();
        for(String op:p.getOPCIONES()){
            HBox hbOp=new HBox();
            RadioButton rb=new RadioButton();
            tg.getToggles().add(rb);
            hbOp.getChildren().addAll(rb,new Label(op));
            hbOp.setPadding(new Insets(0,0,0,10));
            hbOp.setSpacing(15);
            root.getChildren().add(hbOp);
        }
        HBox hbBtnEnviar=new HBox();
        Button btnEnviar=new Button("Enviar");
        btnEnviar.setOnAction(e->{verificarRespuesta(p,tg);});
        hbBtnEnviar.getChildren().add(btnEnviar);
        return paneArbol;
    }
    private void verificarRespuesta(Pregunta pregunta,ToggleGroup tg){
        RadioButton correcto=(RadioButton)tg.getToggles().get(pregunta.getRespuesta());
        if(correcto.isSelected()){
            System.out.println("Correcto");
        }else{
            System.out.println("Jaja, loquitop");
        }
    }
}
