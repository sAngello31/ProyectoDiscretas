/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.ArbolBinario;
import Modelo.Jugador;
import Modelo.Pregunta;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
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
    private Jugador jugadorActual;
    private ArbolBinario<Pregunta> arbolJugador1;
    private ArbolBinario<Pregunta> arbolJugador2;
    private int turno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turno = 1;
        llenarListaBotones();
        arbolJugador1 = crearArbol();
        arbolJugador2 = crearArbol();
        cargarImagenesBotones();
        añadirEventHandlerBtn();
        bloquearBotones(arbolJugador1);
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
        }, Pregunta.class);
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

    private void cargarImagenesBotones() {
        ArbolBinario<Pregunta> ref;
        if(!(turno%2==0)){
            ref=arbolJugador1;
        }else{
            ref=arbolJugador2;
        }
        for (int i = 0; i < 14; i++) {
            Image img1 = App.abrirImagen(App.PATHIMAGES + arbolJugador1.arr[i].getCategoria().name() + ".png", 50, 47.32);
            Image img2 = App.abrirImagen(App.PATHIMAGES + arbolJugador2.arr[i].getCategoria().name() + ".png", 50, 47.32);
            BackgroundImage bgImg1 = new BackgroundImage(img1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background bg = new Background(bgImg1);
            botones.get(i).setStyle("");
            botones.get(i).setBackground(bg);
        }
    }

    private void añadirEventHandlerBtn() {
        for (int i = 0; i < 14; i++) {
            Pregunta p = arbolJugador1.arr[i];
            Pregunta p1 = arbolJugador2.arr[i];
            botones.get(i).setOnAction(e -> {
                mostrarPregunta(p, p1);
            });
        }
    }

    private Pane mostrarPregunta(Pregunta p, Pregunta p2) {
        Pane paneArbol = (Pane) vbRoot.getChildren().remove(vbRoot.getChildren().size() - 1);
        VBox root = new VBox();
        HBox hbEnunciado = new HBox();
        hbEnunciado.getChildren().add(new Label(p.getEnunciado()));
        hbEnunciado.setAlignment(Pos.CENTER);
        root.getChildren().add(hbEnunciado);
        ToggleGroup tg = new ToggleGroup();
        Pregunta preguntaAMostrar;
        if (turno % 2 == 0) {
            preguntaAMostrar = p2;
        } else {
            preguntaAMostrar = p;
        }
        for (String op : preguntaAMostrar.getOPCIONES()) {
            HBox hbOp = new HBox();
            RadioButton rb = new RadioButton();
            tg.getToggles().add(rb);
            hbOp.getChildren().addAll(rb, new Label(op));
            hbOp.setPadding(new Insets(0, 0, 0, 10));
            hbOp.setSpacing(15);
            root.getChildren().add(hbOp);
        }
        HBox hbBtnEnviar = new HBox();
        Button btnEnviar = new Button("Enviar");
        btnEnviar.setOnAction(e -> {
            verificarRespuesta(preguntaAMostrar, tg, paneArbol);
        });
        hbBtnEnviar.getChildren().add(btnEnviar);
        root.getChildren().add(hbBtnEnviar);
        vbRoot.getChildren().add(root);
        return paneArbol;
    }

    private void verificarRespuesta(Pregunta pregunta, ToggleGroup tg, Pane paneArbol) {
        RadioButton correcto = (RadioButton) tg.getToggles().get(pregunta.getRespuesta());
        if (correcto.isSelected()) {
            System.out.println("Correcto");
            cambiarEstadoPreguntas(pregunta, true);
        } else {
            System.out.println("Jaja, loquitop");
            cambiarEstadoPreguntas(pregunta, false);
        }

        vbRoot.getChildren().remove(vbRoot.getChildren().size() - 1);
        vbRoot.getChildren().add(paneArbol);
        cargarImagenesBotones();
        if (!(turno % 2 == 0)) {
            bloquearBotones(arbolJugador1);
        } else {
            bloquearBotones(arbolJugador2);
        }
        turno += 1;
    }

    public void cambiarEstadoPreguntas(Pregunta p, boolean bloqueo) {
        Pregunta[] preguntas;
        if (!(turno % 2 == 0)) {
            preguntas = arbolJugador1.arr;
        } else {
            preguntas = arbolJugador2.arr;
        }
        int indice = -1;
        for (int i = 0; i < preguntas.length; i++) {
            System.out.println("while");
            if (p.equals(preguntas[i])) {
                indice = i + 1;
            }
        }
        if (indice >= 0) {
            Deque<Integer> pila = new ArrayDeque<>();
            pila.push(indice);
            while (!pila.isEmpty()) {
                System.out.println("while");
                Integer ref = pila.pop();
                preguntas[ref].setDisponible(bloqueo);
                if (2 * ref + 2 < 14) {
                    pila.push((2 * ref) + 1);
                    pila.push((2 * ref) + 2);
                }
            }
        }
    }

    public void bloquearBotones(ArbolBinario<Pregunta> preguntas) {
        if (turno == 1) {
            for (int i = 0; i < 2; i++) {
                botones.get(i).setDisable(false);
                arbolJugador1.arr[i].setDisponible(true);
            }
            for (int i = 2; i < 14; i++) {
                botones.get(i).setDisable(true);
            }
        } else if (turno == 2) {
            for (int i = 0; i < 2; i++) {
                botones.get(i).setDisable(false);
                arbolJugador2.arr[i].setDisponible(true);
            }
            for (int i = 2; i < 14; i++) {
                botones.get(i).setDisable(true);
            }
        } else {
            for (int i = 0; i < 14; i++) {
                if (!preguntas.arr[i].isDisponible()) {
                    botones.get(i).setDisable(true);
                }
            }
        }

    }
}
