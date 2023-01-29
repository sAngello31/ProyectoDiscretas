/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.mycompany.proyectodiscretas.App;
import com.mycompany.proyectodiscretas.GanadorController;
import com.mycompany.proyectodiscretas.PreguntaController;
import com.mycompany.proyectodiscretas.PreguntaController1;
import com.mycompany.proyectodiscretas.PreguntaMostradaController;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Angello Bravo
 */
public class Temporizador extends Thread {

    public boolean iniciar = true;
    public int segundos = 15;
    public VBox root;
    public Pregunta p;
    @FXML
    public Label e;

    public Temporizador(VBox e, Pregunta p) {
        this.root = e;
        this.p = p;
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
    public void temporizadorFinalizado() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("SE TE ACABÓ EL TIEMPO");
        a.showAndWait();
        if (App.turno == 2) {
            PreguntaMostradaController.j1 = root;
            PreguntaController.cambiarEstadoPreguntas(p, false);
            PreguntaController.bloquearBotones();
        } else if (App.turno == 3) {
            PreguntaMostradaController.j2 = root;
            PreguntaController1.cambiarEstadoPreguntas(p, false);
            PreguntaController1.bloquearBotones();
            App.changeRoot(PreguntaMostradaController.j1);
        } else if (App.turno % 2 == 1) {
            PreguntaMostradaController.j2 = root;
            PreguntaController1.cambiarEstadoPreguntas(p, false);
            PreguntaController1.bloquearBotones();
            App.changeRoot(PreguntaMostradaController.j1);

        } else if (App.turno % 2 == 0) {
            PreguntaMostradaController.j1 = root;
            PreguntaController.cambiarEstadoPreguntas(p, false);
            PreguntaController.bloquearBotones();
            App.changeRoot(PreguntaMostradaController.j2);
        }
    }
}
