/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.ArbolBinario;
import Modelo.Jugador;
import Modelo.Pregunta;
import Modelo.Temporizador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
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
import javafx.stage.Stage;

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
    private static ArrayList<Button> botones;
    private Jugador jugador1;
    public static ArbolBinario<Pregunta> arbolJugador1;
    public Pregunta[] preguntas;
    public static Pane escenaJ1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arbolJugador1 = crearArbol();
        llenarListaBotones();
        cargarImagenesBotones();
        añadirEventHandlerBtn();
        for (int i = 0; i < 2; i++) {
            arbolJugador1.arr[i].setDisponible(true);
        }
        bloquearBotones();
        lblPlayer.setText("Jugador 1: " + App.listaJugadores.get(0).getUsername());
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
        }, Pregunta.class);
        Collections.shuffle(App.preguntas);
        for (ArrayList<Pregunta> arrP : App.preguntas) {
            Collections.shuffle(arrP);
        }
        if (!App.preguntas.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                for (ArrayList<Pregunta> arrP : App.preguntas) {
                    Iterator<Pregunta> it = arrP.iterator();
                    arbol.encolar(it.next());
                    it.remove();
                }
                Iterator<Pregunta> it = App.preguntas.get(i).iterator();
                arbol.encolar(it.next());
                it.remove();
            }
        }
        return arbol;

    }

    private void cargarImagenesBotones() {
        for (int i = 0; i < 14; i++) {
            Image img1 = App.abrirImagen(App.PATHIMAGES + arbolJugador1.arr[i].getCategoria().name() + ".png", 50, 47.32);
            BackgroundImage bgImg1 = new BackgroundImage(img1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background bg = new Background(bgImg1);
            botones.get(i).setStyle("");
            botones.get(i).setBackground(bg);
        }
    }

    private void añadirEventHandlerBtn() {
        for (int i = 0; i < botones.size(); i++) {
            Pregunta p = arbolJugador1.arr[i];
            botones.get(i).setOnAction(e -> {
                try {
                    escogerPregunta(p);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
    }

    public VBox obtenerArbolJugador() {
        return vbRoot;
    }

    @FXML
    private void escogerPregunta(Pregunta p1) throws IOException {

        FXMLLoader fxml = new FXMLLoader(App.class.getResource("PreguntaMostrada.fxml"));
        PreguntaMostradaController ct = new PreguntaMostradaController();
        ct.tempo=new Temporizador(obtenerArbolJugador(),p1,arbolJugador1.arr);
        fxml.setController(ct);
        ct.preguntasref=arbolJugador1.arr;
        VBox root = (VBox) fxml.load();

        ct.llenarDatos(p1);
        ct.mostrarPreguntas(p1, obtenerArbolJugador());
        App.changeRoot(root);
    }

    public static int cambiarEstadoPreguntas(Pregunta p, boolean bloqueo) {
        Pregunta[] preguntas = arbolJugador1.arr;
        int indice = -1;
        int retorno=-1;
        for (int i = 0; i < preguntas.length; i++) {
            System.out.println("while");
            if (p.equals(preguntas[i])) {
                indice = i;
            }
        }
        if (indice >= 0) {
            retorno=indice;
            if (bloqueo) {
                preguntas[indice].setDisponible(bloqueo);
                if(2 * (indice + 1) + 1 < 14){
                    preguntas[(2 * (indice + 1))].setDisponible(bloqueo);
                    preguntas[(2 * (indice + 1))+1].setDisponible(bloqueo);
                }
            } else {
                Deque<Integer> pila = new ArrayDeque<>();
                pila.push(indice);
                while (!pila.isEmpty()) {
                    System.out.println("while");
                    Integer ref = pila.pop();
                    preguntas[ref].setDisponible(bloqueo);
                    if (2 * (ref + 1) + 1 < 14) {
                        pila.push((2 * (ref + 1)));
                        pila.push((2 * (ref + 1)) + 1);
                    }
                }
            }
        }
        return retorno;
    }

    public static boolean bloquearBotones() {
        boolean todosBloqueados=true;
        for (int i = 0; i < 14; i++) {
            if (!arbolJugador1.arr[i].isDisponible()) {
                botones.get(i).setDisable(true);
            } else {
                botones.get(i).setDisable(false);
            }
            todosBloqueados=todosBloqueados&&botones.get(i).isDisable();
        }
        return todosBloqueados;
    }
    public static void borrarEventHandler(int indice){
        botones.get(indice).setOnAction(null);
    }
    public static boolean todasRespondidas(){
        Pregunta[] preguntas = arbolJugador1.arr;
        boolean retorno=true;
        for(int i=0;i<preguntas.length;i++){
            System.out.println(preguntas[i].getId());
            retorno=retorno&&preguntas[i].isRespondida();
        }
        return retorno;
    }
        public static void cambiarRespondida(Pregunta p) {
        Pregunta[] preguntas = arbolJugador1.arr;
        int indice = -1;
        for (int i = 0; i < preguntas.length; i++) {
            if (p.equals(preguntas[i])) {
                indice = i;
            }
        }
        if (indice >= 0) {
            Deque<Integer> pila = new ArrayDeque<>();
            pila.push(indice);
            while (!pila.isEmpty()) {
                System.out.println("while");
                Integer ref = pila.pop();
                preguntas[ref].setRespondida(true);
                if (2 * (ref + 1) + 1 < 14) {
                    pila.push((2 * (ref + 1)));
                    pila.push((2 * (ref + 1)) + 1);
                }
            }
        }
    }
}
