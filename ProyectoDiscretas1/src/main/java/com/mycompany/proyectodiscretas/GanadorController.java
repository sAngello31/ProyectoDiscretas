/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodiscretas;

import Modelo.Jugador;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author jexa1
 */
public class GanadorController implements Initializable {

    @FXML
    ImageView imgvfelicidades;
    @FXML
    private Label lblFelicitacion;
    public static Jugador ganador;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblFelicitacion.setText(ganador.getUsername()+" has ganado");
        try ( FileInputStream input = new FileInputStream("Popup/" + "felicitaciones.gif")) {
                Image image = new Image(input, 598, 304, false, false);
                imgvfelicidades.setImage(image);
            } catch (IOException e) {
                System.out.println("Archivo no encontrado");
            }
        
    }    
    
}
