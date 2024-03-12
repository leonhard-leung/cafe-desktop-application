package com.leonhard_leung;

import com.leonhard_leung.view.LandingPageView;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClientInterfaceTest extends Application {

    public static void main(String[] args) {
        launch(args);
    } // end of main

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/landing_page.fxml"));
        Parent landingPage = loader.load();
        Parent signInSignUpPage = FXMLLoader.load(getClass().getResource("/fxml/sign_in_sign_up_page.fxml"));
        Scene signInSignUpPageScene = new Scene(signInSignUpPage);

        Scene scene = new Scene(landingPage);
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

        view.getSignInBT().setOnMouseClicked(event -> {
            view.setCircleVisibility(true);
            ScaleTransition transition1 = view.setCircleScaleTransition(Duration.seconds(1), view.getCircle1(), 1, 250);
            ScaleTransition transition2 = view.setCircleScaleTransition(Duration.seconds(0.7), view.getCircle2(), 1, 250);

            transition1.play();
            transition2.play();


            transition1.setOnFinished(event1 -> {
                view.setCircleVisibility(false);
                stage.setScene(signInSignUpPageScene);
            });
        });

        view.getSignUpBT().setOnMouseClicked(event -> {
            view.setCircleVisibility(true);
            ScaleTransition transition1 = view.setCircleScaleTransition(Duration.seconds(1), view.getCircle1(), 1, 250);
            ScaleTransition transition2 = view.setCircleScaleTransition(Duration.seconds(0.7), view.getCircle2(), 1, 250);

            transition1.play();
            transition2.play();

            transition1.setOnFinished(event1 -> {
                view.setCircleVisibility(false);
                stage.setScene(signInSignUpPageScene);
            });


                /*
                view.menuPane.setVisible(false);
                view.stackPane.getChildren().setAll(signInSignUpContent);

                 */
        });


        stage.show();
        view.getSignInBT().setOnAction(event -> System.out.println("Login Pressed"));
    } // end of start
} // end of ClientInterfaceTest
