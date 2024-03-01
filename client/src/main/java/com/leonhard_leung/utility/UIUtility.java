package com.leonhard_leung.utility;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class UIUtility {

    public static void applyPrimaryButtonEffectsWithTranslation(Node node) {
        TranslateTransition normalTransition = new TranslateTransition(Duration.millis(100), node);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), node);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(250), node);
        FadeTransition clickIn = new FadeTransition(Duration.millis(150), node);
        FadeTransition clickOut = new FadeTransition(Duration.millis(150), node);

        fadeIn.setToValue(0.7);
        fadeOut.setToValue(1);
        clickIn.setFromValue(0.7);
        clickIn.setToValue(1);
        clickOut.setFromValue(1);
        clickOut.setToValue(0.7);

        node.setOnMouseClicked(actionEvent -> {
            clickIn.playFromStart();
            clickOut.setDelay(Duration.millis(150));
            clickOut.play();
        });

        node.setOnMouseEntered(event -> {
            double originalY = node.getTranslateY();
            double desiredY = originalY - 3;

            fadeIn.playFromStart();
            normalTransition.setByY(desiredY - originalY);
            normalTransition.playFromStart();
        });

        node.setOnMouseExited(event -> {
            fadeOut.playFromStart();
            normalTransition.stop();
            normalTransition.setByY(-node.getTranslateY());
            normalTransition.playFromStart();
        });
    }

    public static void applyPrimaryButtonEffects(Node node) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), node);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(250), node);
        FadeTransition clickIn = new FadeTransition(Duration.millis(150), node);
        FadeTransition clickOut = new FadeTransition(Duration.millis(150), node);

        fadeIn.setToValue(0.7);
        fadeOut.setToValue(1);
        clickIn.setFromValue(0.7);
        clickIn.setToValue(1);
        clickOut.setFromValue(1);
        clickOut.setToValue(0.7);

        node.setOnMouseClicked(actionEvent -> {
            clickIn.playFromStart();
            clickOut.setDelay(Duration.millis(150));
            clickOut.play();
        });

        node.setOnMouseEntered(event -> {
            fadeIn.playFromStart();
        });

        node.setOnMouseExited(event -> {
            fadeOut.playFromStart();
        });
    }

    public static void applySecondaryButtonEffects(Node node) {
        FadeTransition clickIn = new FadeTransition(Duration.millis(150), node);
        FadeTransition clickOut = new FadeTransition(Duration.millis(150), node);

        clickIn.setFromValue(1);
        clickIn.setToValue(0.7);
        clickOut.setFromValue(0.7);
        clickOut.setToValue(1);

        node.setOnMouseClicked(actionEvent -> {
            clickIn.play();
            clickOut.setDelay(Duration.millis(150));
            clickOut.play();
        });
    }

    public static void applyImageScreenShake(Node node) {
        TranslateTransition shakeTransition1 = new TranslateTransition(Duration.millis(50), node);
        shakeTransition1.setByX(2);
        shakeTransition1.setAutoReverse(true);
        shakeTransition1.setCycleCount(2);

        TranslateTransition shakeTransition2 = new TranslateTransition(Duration.millis(50), node);
        shakeTransition2.setByX(-2);
        shakeTransition2.setAutoReverse(true);
        shakeTransition2.setCycleCount(2);

        node.setOnMouseEntered(event -> {
            shakeTransition1.playFromStart();
            shakeTransition1.setOnFinished(e -> shakeTransition2.playFromStart());
        });

        node.setOnMouseExited(event -> {
            shakeTransition1.stop();
            shakeTransition2.stop();

            node.setTranslateX(0);
            node.setTranslateY(0);
        });
    }
}
