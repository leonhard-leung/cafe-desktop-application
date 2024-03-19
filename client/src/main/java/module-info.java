module client {
    requires common;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    opens com.leonhard_leung.view.landing_page to javafx.fxml;
    opens com.leonhard_leung.view.main_view to javafx.fxml;

    exports com.leonhard_leung;
    exports com.leonhard_leung.view.landing_page;
    exports com.leonhard_leung.view.main_view;
}