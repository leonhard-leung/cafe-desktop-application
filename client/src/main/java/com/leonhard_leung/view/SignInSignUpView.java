package com.leonhard_leung.view;

import com.leonhard_leung.utility.UIUtility;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.Objects;

public class SignInSignUpView {
    @FXML
    private Button backToLandingPageBT1;
    @FXML
    private Button backToLandingPageBT2;
    @FXML
    private Button goToSignInBT;
    @FXML
    private Button goToSignUpBT;
    @FXML
    private Button signInBT;
    @FXML
    private Button signUpBT;
    @FXML
    private Rectangle leftRectangle;
    @FXML
    private Rectangle rightRectangle;
    @FXML
    private Pane signUpPaneSlider;
    @FXML
    private Pane signInPaneSlider;
    @FXML
    private Pane signUpContent;
    @FXML
    private Pane signInContent;
    @FXML
    private TextField signInEmailField;
    @FXML
    private TextField signUpUsernameField;
    @FXML
    private TextField signUpEmailField;
    @FXML
    private TextField revealSignInPasswordField;
    @FXML
    private TextField revealSignUpPasswordField;
    @FXML
    private TextField revealSignUpConfirmPasswordField;
    @FXML
    private PasswordField signInPasswordField;
    @FXML
    private PasswordField signUpPasswordField;
    @FXML
    private PasswordField signUpConfirmPasswordField;
    @FXML
    private ImageView signInPasswordImage;
    @FXML
    private ImageView signUpPasswordImage;
    @FXML
    private ImageView signUpConfirmPasswordImage;

    @FXML
    private final Image eyeCrossedIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/landing_page/sign_in_sign_up/eye_crossed.png")).toExternalForm());
    @FXML
    private final Image eyeIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/landing_page/sign_in_sign_up/eye.png")).toExternalForm());

    @FXML
    public void initialize() {
        signUpContent.setVisible(false);
        setupNavigationBehavior();
        setupButtonAnimations();
        setupPasswordIconActions();
    } // end of initialize

    private void setupButtonAnimations() {
        UIUtility.applyPrimaryButtonEffects(signInBT);
        UIUtility.applyPrimaryButtonEffects(signUpBT);
        UIUtility.applySecondaryButtonEffects(goToSignInBT);
        UIUtility.applySecondaryButtonEffects(goToSignUpBT);
        UIUtility.applySecondaryButtonEffects(backToLandingPageBT1);
        UIUtility.applySecondaryButtonEffects(backToLandingPageBT2);
    } // end of setButtonAnimations

    private void setupNavigationBehavior() {
        goToSignInBT.setOnMouseClicked(event -> {
            playTransition(true);

            signInPasswordImage.setImage(eyeIcon);
            revealSignInPasswordField.setVisible(false);
            signInPasswordField.setVisible(true);

            signInEmailField.clear();
            signInPasswordField.clear();
            revealSignInPasswordField.clear();
        });
        goToSignUpBT.setOnMouseClicked(event -> {
            playTransition(false);

            signUpPasswordImage.setImage(eyeIcon);
            signUpConfirmPasswordImage.setImage(eyeIcon);
            revealSignUpPasswordField.setVisible(false);
            revealSignUpConfirmPasswordField.setVisible(false);
            signUpPasswordField.setVisible(true);
            signUpConfirmPasswordField.setVisible(true);

            signUpUsernameField.clear();
            signUpEmailField.clear();
            signUpPasswordField.clear();
            signUpConfirmPasswordField.clear();
            revealSignUpPasswordField.clear();
            revealSignUpConfirmPasswordField.clear();
        });
    } // end of setupSignInSignUpBehavior

    private void setupPasswordIconActions() {
        signInPasswordImage.setImage(eyeIcon);
        signUpPasswordImage.setImage(eyeIcon);
        signUpConfirmPasswordImage.setImage(eyeIcon);

        revealSignInPasswordField.setVisible(false);
        revealSignUpPasswordField.setVisible(false);
        revealSignUpConfirmPasswordField.setVisible(false);

        setPasswordIconAction(signInPasswordImage, signInPasswordField, revealSignInPasswordField);
        setPasswordIconAction(signUpPasswordImage, signUpPasswordField, revealSignUpPasswordField);
        setPasswordIconAction(signUpConfirmPasswordImage, signUpConfirmPasswordField, revealSignUpConfirmPasswordField);
    } // end of setupPasswordIconActions

    private void playTransition(boolean isSignIn) {
        double initialLeftArcWidth = 0, initialLeftArcHeight = 0, initialRightArcWidth = 0, initialRightArcHeight = 0;
        double leftArcWidth = 0, leftArcHeight = 0, rightArcWidth = 0, rightArcHeight = 0;
        double signInContentValue = 0, signUpContentValue = 0, signInPaneSliderValue = 0, signUpPaneSliderValue = 0,
                leftRectangleValue = 0, rightRectangleValue = 0;

        TranslateTransition signInContentTransition = new TranslateTransition(Duration.seconds(0.8), signInContent);
        TranslateTransition signUpContentTransition = new TranslateTransition(Duration.seconds(0.8), signUpContent);
        TranslateTransition signInPaneSliderTransition = new TranslateTransition(Duration.seconds(0.8), signInPaneSlider);
        TranslateTransition signUpPaneSliderTransition = new TranslateTransition(Duration.seconds(0.8), signUpPaneSlider);
        TranslateTransition leftRectangleTransition = new TranslateTransition(Duration.seconds(0.7), leftRectangle);
        TranslateTransition rightRectangleTransition = new TranslateTransition(Duration.seconds(0.7), rightRectangle);

        KeyFrame signInContentKeyFrame;
        KeyFrame signUpContentKeyFrame;

        if (isSignIn) {
            initialRightArcWidth = 300;
            initialRightArcHeight = 300;
            leftArcWidth = 300;
            leftArcHeight = 300;

            signInContentKeyFrame = new KeyFrame(Duration.seconds(0.2), event -> signInContent.setVisible(true));
            signUpContentKeyFrame = new KeyFrame(Duration.seconds(0.4), event -> signUpContent.setVisible(false));
        } else {
            initialLeftArcWidth = 300;
            initialLeftArcHeight = 300;
            rightArcWidth = 300;
            rightArcHeight = 300;

            signUpContentValue = 275;
            signInContentValue = 275;
            signUpPaneSliderValue = 550;
            signInPaneSliderValue = 550;
            leftRectangleValue = -550;
            rightRectangleValue = -550;

            signInContentKeyFrame = new KeyFrame(Duration.seconds(0.4), event -> signInContent.setVisible(false));
            signUpContentKeyFrame = new KeyFrame(Duration.seconds(0.2), event -> signUpContent.setVisible(true));
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(leftRectangle.arcWidthProperty(), initialLeftArcWidth),
                        new KeyValue(rightRectangle.arcWidthProperty(), initialRightArcWidth),
                        new KeyValue(leftRectangle.arcHeightProperty(), initialLeftArcHeight),
                        new KeyValue(rightRectangle.arcHeightProperty(), initialRightArcHeight)),
                new KeyFrame(Duration.seconds(0.7),
                        new KeyValue(leftRectangle.arcWidthProperty(), leftArcWidth),
                        new KeyValue(rightRectangle.arcWidthProperty(), rightArcWidth),
                        new KeyValue(leftRectangle.arcHeightProperty(), leftArcHeight),
                        new KeyValue(rightRectangle.arcHeightProperty(), rightArcHeight)),
                signInContentKeyFrame,
                signUpContentKeyFrame
        );

        signInContentTransition.setToX(signInContentValue);
        signUpContentTransition.setToX(signUpContentValue);
        signInPaneSliderTransition.setToX(signInPaneSliderValue);
        signUpPaneSliderTransition.setToX(signUpPaneSliderValue);
        leftRectangleTransition.setToX(leftRectangleValue);
        rightRectangleTransition.setToX(rightRectangleValue);

        signInContentTransition.play();
        signUpContentTransition.play();
        signInPaneSliderTransition.play();
        signUpPaneSliderTransition.play();
        leftRectangleTransition.play();
        rightRectangleTransition.play();
        timeline.play();
    } // end of playTransition

    public void setSigninComponentPlacements() {
        hideComponents();

        signInContent.setTranslateX(0);
        signInContent.setTranslateY(0);
        signUpContent.setTranslateX(0);
        signUpContent.setTranslateY(0);
        signInPaneSlider.setTranslateX(0);
        signUpPaneSlider.setTranslateX(0);
        leftRectangle.setTranslateX(0);
        rightRectangle.setTranslateX(0);

        leftRectangle.arcWidthProperty().set(300);
        leftRectangle.arcHeightProperty().set(300);
        rightRectangle.arcWidthProperty().set(0);
        rightRectangle.arcHeightProperty().set(0);
    } // end of setSigninComponentPlacements

    public void setSignupComponentPlacements() {
        hideComponents();

        signUpContent.setTranslateX(275);
        signUpContent.setTranslateY(0);
        signInContent.setTranslateX(275);
        signInContent.setTranslateY(0);
        signUpPaneSlider.setTranslateX(550);
        signInPaneSlider.setTranslateX(550);
        leftRectangle.setTranslateX(-550);
        rightRectangle.setTranslateX(-550);

        leftRectangle.arcWidthProperty().set(0);
        leftRectangle.arcHeightProperty().set(0);
        rightRectangle.arcWidthProperty().set(300);
        rightRectangle.arcHeightProperty().set(300);
    } // end of setSignupComponentPlacements

    public void playEnterTransition(boolean isSignIn) {
        double initialLeftArcWidth = 0, initialLeftArcHeight = 0, initialRightArcWidth = 0, initialRightArcHeight = 0;
        double leftArcWidth = 0, leftArcHeight = 0, rightArcWidth = 0, rightArcHeight = 0;

        TranslateTransition signUpContentTransition = new TranslateTransition(Duration.seconds(1), signUpContent);
        TranslateTransition signInContentTransition = new TranslateTransition(Duration.seconds(1), signInContent);
        TranslateTransition signUpPaneSliderTransition = new TranslateTransition(Duration.seconds(0.7), signUpPaneSlider);
        TranslateTransition signInPaneSliderTransition = new TranslateTransition(Duration.seconds(0.7), signInPaneSlider);
        TranslateTransition leftRectangleTransition = new TranslateTransition(Duration.seconds(0.7), leftRectangle);
        TranslateTransition rightRectangleTransition = new TranslateTransition(Duration.seconds(0.7), rightRectangle);

        signUpContentTransition.setToY(0);
        signInContentTransition.setToY(0);

        clearAllTextFields();
        if (isSignIn) {
            initialRightArcWidth = 300;
            initialRightArcHeight = 300;
            leftArcWidth = 300;
            leftArcHeight = 300;

            signUpPaneSliderTransition.setToX(0);
            leftRectangleTransition.setToX(0);
            rightRectangleTransition.setToX(0);

            signInContent.setTranslateY(-700);
            signUpPaneSlider.setTranslateX(550);
            leftRectangle.setTranslateX(550);
            rightRectangle.setTranslateX(550);
        } else {
            initialLeftArcWidth = 300;
            initialLeftArcHeight = 300;
            rightArcWidth = 300;
            rightArcHeight = 300;

            signInPaneSliderTransition.setToX(550);
            leftRectangleTransition.setToX(-550);
            rightRectangleTransition.setToX(-550);

            signUpContent.setTranslateY(-700);
            signInPaneSlider.setTranslateX(0);
            leftRectangle.setTranslateX(-1100);
            rightRectangle.setTranslateX(-1100);
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(leftRectangle.arcWidthProperty(), initialLeftArcWidth),
                        new KeyValue(rightRectangle.arcWidthProperty(), initialRightArcWidth),
                        new KeyValue(leftRectangle.arcHeightProperty(), initialLeftArcHeight),
                        new KeyValue(rightRectangle.arcHeightProperty(), initialRightArcHeight)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(leftRectangle.arcWidthProperty(), leftArcWidth),
                        new KeyValue(rightRectangle.arcWidthProperty(), rightArcWidth),
                        new KeyValue(leftRectangle.arcHeightProperty(), leftArcHeight),
                        new KeyValue(rightRectangle.arcHeightProperty(), rightArcHeight))
        );
        timeline.play();

        PauseTransition delayTransition = new PauseTransition(Duration.seconds(0.2));
        delayTransition.setOnFinished(event -> {
            signInPaneSlider.setVisible(true);
            signUpPaneSlider.setVisible(true);
            leftRectangle.setVisible(true);
            rightRectangle.setVisible(true);

            if (isSignIn) {
                signInContent.setVisible(true);
                signUpContent.setVisible(false);
                signInContentTransition.play();
                signUpPaneSliderTransition.play();
            } else {
                signInContent.setVisible(false);
                signUpContent.setVisible(true);
                signUpContentTransition.play();
                signInPaneSliderTransition.play();
            }
            leftRectangleTransition.play();
            rightRectangleTransition.play();
        });
        delayTransition.play();
    } // end of playEnterTransition

    public TranslateTransition playExitTransition(boolean isSignIn) {
        TranslateTransition signUpContentTransition = new TranslateTransition(Duration.seconds(0.7), signUpContent);
        TranslateTransition signInContentTransition = new TranslateTransition(Duration.seconds(0.7), signInContent);
        TranslateTransition signUpPaneSliderTransition = new TranslateTransition(Duration.seconds(0.7), signUpPaneSlider);
        TranslateTransition signInPaneSliderTransition = new TranslateTransition(Duration.seconds(0.7), signInPaneSlider);
        TranslateTransition leftRectangleTransition = new TranslateTransition(Duration.seconds(0.7), leftRectangle);
        TranslateTransition rightRectangleTransition = new TranslateTransition(Duration.seconds(0.7), rightRectangle);

        signUpContentTransition.setToY(-700);
        signInContentTransition.setToY(-700);
        if (isSignIn) {
            signUpPaneSliderTransition.setToX(550);
            leftRectangleTransition.setToX(550);
            rightRectangleTransition.setToX(550);
        } else {
            signInPaneSliderTransition.setToX(0);
            leftRectangleTransition.setToX(-1100);
            rightRectangleTransition.setToX(-1100);
        }
        leftRectangleTransition.play();
        rightRectangleTransition.play();

        signInContentTransition.play();
        signUpPaneSliderTransition.play();
        signUpContentTransition.play();
        signInPaneSliderTransition.play();

        return signUpContentTransition;
    } // end of playExitTransition

    private void setPasswordIconAction(ImageView passwordImage, PasswordField passwordField, TextField revealPasswordField) {
        passwordImage.setOnMouseClicked(event -> {
            if (passwordImage.getImage() == eyeIcon) {
                passwordImage.setImage(eyeCrossedIcon);
                revealPasswordField.setText(passwordField.getText());
                passwordField.setVisible(false);
                revealPasswordField.setVisible(true);
                revealPasswordField.requestFocus();
            } else {
                passwordImage.setImage(eyeIcon);
                passwordField.setText(revealPasswordField.getText());
                passwordField.setVisible(true);
                revealPasswordField.setVisible(false);
                passwordField.requestFocus();
            }
        });
    } // end of setPasswordIconAction

    private void hideComponents() {
        signInContent.setVisible(false);
        signUpContent.setVisible(false);
        signInPaneSlider.setVisible(false);
        signUpPaneSlider.setVisible(false);
        leftRectangle.setVisible(false);
        rightRectangle.setVisible(false);
    } // end of hideComponents

    private void clearAllTextFields() {
        signInEmailField.clear();
        signInPasswordField.clear();
        revealSignInPasswordField.clear();

        signUpUsernameField.clear();
        signUpEmailField.clear();
        signUpPasswordField.clear();
        signUpConfirmPasswordField.clear();
        revealSignUpPasswordField.clear();
        revealSignUpConfirmPasswordField.clear();
    } // end of clearAllTextFields

    public Button getBackToLandingPageBT1() {
        return backToLandingPageBT1;
    }

    public Button getBackToLandingPageBT2() {
        return backToLandingPageBT2;
    }
} // end of SignInSignUpView class
