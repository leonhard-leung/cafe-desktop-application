module client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    opens com.leonhard_leung.view to javafx.fxml;

    exports com.leonhard_leung;
    exports com.leonhard_leung.view;
}