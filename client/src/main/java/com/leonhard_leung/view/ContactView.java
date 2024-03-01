package com.leonhard_leung.view;

import com.leonhard_leung.utility.UIUtility;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ContactView {
    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        setComponentAnimations();
    } // end of initialize

    private void setComponentAnimations() {
        UIUtility.applyPrimaryButtonEffects(submitButton);
    }

} // end of ContactView
