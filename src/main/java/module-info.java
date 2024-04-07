module com.z01a.tankgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.z01a.tankgame to javafx.fxml;
    exports com.z01a.tankgame;
}