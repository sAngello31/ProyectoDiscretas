/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.Pregunta;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author jexa1
 */
public class PreguntaRealizadaController implements Initializable {
    static Pregunta pregunta;
    @FXML
    RadioButton rb1;
    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb3;
    @FXML
    RadioButton rb4;
    @FXML
    Label lblPregunta;
    @FXML
    Label lblOp1;
    @FXML
    Label lblOp2;
    @FXML
    Label lblOp3;
    @FXML
    Label lblOp4;
    @FXML
    Button btnResponder;
    ArrayList<RadioButton> radioButtons=new ArrayList<>();
    ArrayList<Label> lblsOpciones=new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarListaRadioButtons();
        llenarListaLabelOpciones();
        cargarPregunta();
        btnResponder.setOnAction(e->{
            verificarRespuesta();
        });
    }    
    public void cargarPregunta(){
        ArrayList<String> opciones=pregunta.getOPCIONES();
        lblPregunta.setText(pregunta.getEnunciado());
        for(int i=0;i<4;i++){
            lblsOpciones.get(i).setText(opciones.get(i));
        }
    }
    public void llenarListaRadioButtons(){
        radioButtons.add(rb1);
        radioButtons.add(rb2);
        radioButtons.add(rb3);
        radioButtons.add(rb4);
    }
    public void llenarListaLabelOpciones(){
        lblsOpciones.add(lblOp1);
        lblsOpciones.add(lblOp2);
        lblsOpciones.add(lblOp3);
        lblsOpciones.add(lblOp4);
    }
    public void verificarRespuesta(){
        if(radioButtons.get(pregunta.getRespuesta()).isSelected()){
            System.out.println("Correcto");
        }else{
            System.out.println("Jaja, loquitop");
        }
    }
    
}
