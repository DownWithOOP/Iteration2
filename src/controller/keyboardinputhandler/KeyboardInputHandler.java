package controller.keyboardinputhandler;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import model.Game;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jordi on 2/21/2017.
 */
public class KeyboardInputHandler implements Initializable {

    @FXML
    private Label keyPressed;


    //store the key being pressed
    private KeyCode keyCode;


    @FXML
    public void keyListener(KeyEvent event) {
        keyCode = event.getCode();
        System.out.println(keyCode.toString());
        keyPressed.setText("Key Pressed: " + keyCode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
