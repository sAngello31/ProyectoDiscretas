package com.mycompany.proyectodiscretas;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable{
    
    @FXML
    ImageView imgvarbol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try ( FileInputStream input = new FileInputStream("Popup/" + "arbolito.png")) {
            Image image = new Image(input, 307, 317, false, false);
            imgvarbol.setImage(image);
        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
        }
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    
}
