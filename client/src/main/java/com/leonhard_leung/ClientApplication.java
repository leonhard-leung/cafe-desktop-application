package com.leonhard_leung;

import com.leonhard_leung.view.LandingPageView;
import com.leonhard_leung.view.SignInSignUpView;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class ClientApplication extends Application {
    private Scene landingPage;
    private Scene signInSignUpPage;
    private LandingPageView landingPageView;
    private SignInSignUpView signInSignUpView;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/images/app_logo.png")).toExternalForm()));
        Font.loadFont(getClass().getResourceAsStream("/fonts/Chewy.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Louis George Cafe.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/RobotoCondensed-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/FredokaOne-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/MoonLookDemoRegular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/HappyCaramelDemoRegular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Boldstrom D.otf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/SmartBold.ttf"), 12);

        stage.setTitle("Love & Latte Lounge");
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.centerOnScreen();

        setupScenes();
        setupSignInSignUpButtons();
        setupBackToLandingPageButton();

        stage.setScene(landingPage);

        // to avoid the cursor from pointing to the first text field when the scene switches to the signInSignUp Page
        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null && newScene == signInSignUpPage) {
                newScene.getRoot().requestFocus();
            }
        });

        //stage.getScene().setFill(Color.web("#a35061"));
        stage.show();
    } // end of start

    private void setupScenes() throws IOException {
        FXMLLoader landingPageLoader = new FXMLLoader(getClass().getResource("/fxml/landing_page.fxml"));
        FXMLLoader signInSignUpPageLoader = new FXMLLoader(getClass().getResource("/fxml/sign_in_sign_up_page.fxml"));

        landingPage = new Scene(landingPageLoader.load());
        signInSignUpPage = new Scene(signInSignUpPageLoader.load());

        landingPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/landing_page.css")).toExternalForm());
        signInSignUpPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/sign_in_sign_up_page.css")).toExternalForm());

        landingPageView = landingPageLoader.getController();
        signInSignUpView = signInSignUpPageLoader.getController();
    } // end of setupScenes

    private void setupSignInSignUpButtons() {
        landingPageView.getSignInBT().setOnMouseClicked(event -> {
            landingPageView.setCircleVisibility(true);
            ScaleTransition transition1 = landingPageView.setCircleScaleTransition(Duration.seconds(1), landingPageView.getCircle1(), 1, 250);
            ScaleTransition transition2 = landingPageView.setCircleScaleTransition(Duration.seconds(0.7), landingPageView.getCircle2(), 1, 250);

            transition1.play();
            transition2.play();

            signInSignUpView.setSigninComponentPlacements();
            transition1.setOnFinished(event1 -> {
                primaryStage.setScene(signInSignUpPage);
                signInSignUpView.playEnterTransition(true);
            });
        });

        landingPageView.getSignUpBT().setOnMouseClicked(event -> {
            landingPageView.setCircleVisibility(true);

            ScaleTransition transition1 = landingPageView.setCircleScaleTransition(Duration.seconds(1), landingPageView.getCircle1(), 1, 250);
            ScaleTransition transition2 = landingPageView.setCircleScaleTransition(Duration.seconds(0.7), landingPageView.getCircle2(), 1, 250);

            signInSignUpView.setSignupComponentPlacements();
            transition1.setOnFinished(event1 -> {
                primaryStage.setScene(signInSignUpPage);
                signInSignUpView.playEnterTransition(false);
            });

            transition1.play();
            transition2.play();
        });
    } // end of setupSignInSignUpButtons

    private void setupBackToLandingPageButton() {
        signInSignUpView.getBackToLandingPageBT1().setOnAction(event -> {
            TranslateTransition transition = signInSignUpView.playExitTransition(false);

            transition.setOnFinished(event1 -> {
                primaryStage.setScene(landingPage);
                landingPageView.setCircleVisibility(true);

                ScaleTransition transition1 = landingPageView.setCircleScaleTransition(Duration.seconds(1), landingPageView.getCircle1(), 250, 1);
                ScaleTransition transition2 = landingPageView.setCircleScaleTransition(Duration.seconds(1), landingPageView.getCircle2(), 250, 1);

                transition1.setOnFinished(event2 -> landingPageView.setCircleVisibility(false));
                transition1.play();
                transition2.play();
            });
        });

        signInSignUpView.getBackToLandingPageBT2().setOnAction(event -> {
            TranslateTransition transition = signInSignUpView.playExitTransition(true);

            transition.setOnFinished(event1 -> {
                primaryStage.setScene(landingPage);
                landingPageView.setCircleVisibility(true);

                ScaleTransition transition1 = landingPageView.setCircleScaleTransition(Duration.seconds(1), landingPageView.getCircle1(), 250, 1);
                ScaleTransition transition2 = landingPageView.setCircleScaleTransition(Duration.seconds(1), landingPageView.getCircle2(), 250, 1);

                transition1.setOnFinished(event2 -> landingPageView.setCircleVisibility(false));
                transition1.play();
                transition2.play();
            });
        });
    } // end of setupBackToLandingPageButton
} // end of ClientApplication
