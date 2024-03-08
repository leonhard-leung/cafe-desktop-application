package com.leonhard_leung.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ProductView {
    @FXML
    private SplitPane splitPane;
    @FXML
    private ScrollPane beverageScrollPane;
    @FXML
    private ScrollPane foodScrollPane;
    @FXML
    private Pane beverageHeaderPane;
    @FXML
    private Pane foodHeaderPane;

    public void onBeveragesEntered() {
        animateHeader(beverageHeaderPane, 165.0);
        animateDivider(splitPane, 0.8175);
    } // end of onBeveragesEntered

    public void onBeveragesExited() {
        animateHeader(beverageHeaderPane, 0.0);
        animateDivider(splitPane, 0.5);
        beverageScrollPane.setVvalue(0);
    } // end of onBeveragesExited

    public void onFoodEntered() {
        animateHeader(foodHeaderPane, 165.0);
        animateDivider(splitPane, 0.1825);
    } // end of onFoodEntered

    public void onFoodExited() {
        animateHeader(foodHeaderPane, 0.0);
        animateDivider(splitPane, 0.5);
        foodScrollPane.setVvalue(0);
    } // end of onFoodExited

    private void animateDivider(SplitPane splitPane, double targetPosition) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), new KeyValue(splitPane.getDividers().get(0).positionProperty(), targetPosition))
        );
        timeline.play();
    } // end of animateDivider

    private void animateHeader(Pane pane, double targetTranslateX) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);
        transition.setToX(targetTranslateX);

        transition.play();
    } // end of animateHeader
} // end of ProductView class
