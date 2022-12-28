module com.mycompany.proyectodiscretas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyectodiscretas to javafx.fxml;
    exports com.mycompany.proyectodiscretas;
}
