module com.example.stair {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.stair to javafx.fxml;
    exports com.example.stair;
    exports com.example.stair.error;
    opens com.example.stair.error to javafx.fxml;
}