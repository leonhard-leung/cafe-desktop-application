package com.leonhard_leung;

import com.leonhard_leung.view.LandingPageView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ClientInterfaceTest extends Application {

    public static void main(String[] args) {
        launch(args);
    } // end of main

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/landing_page.fxml"));
        Scene scene = new Scene(loader.load());
        LandingPageView view = loader.getController();
        Font.loadFont(getClass().getResourceAsStream("/fonts/Chewy.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Louis George Cafe.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoCondensed-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/FredokaOne-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/MoonLookDemoRegular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/HappyCaramelDemoRegular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Boldstrom D.otf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/SmartBold.ttf"), 12);
        scene.getStylesheets().add(getClass().getResource("/css/landing_page.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResource("/images/app_logo.png").toExternalForm()));
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.centerOnScreen();
        stage.show();
        view.getLoginBT().setOnAction(event -> System.out.println("Login Pressed"));
    } // end of start
} // end of ClientInterfaceTest
