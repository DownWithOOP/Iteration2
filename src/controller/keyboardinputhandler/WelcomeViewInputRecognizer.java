package controller.keyboardinputhandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jordi on 2/21/2017.
 */
public class WelcomeViewInputRecognizer implements Initializable {

    @FXML
    private Button startGameButton;
    @FXML
    private Label keyPressed;


    //store the key being pressed
    private KeyCode keyCode;

    //helper method for starting the game
    @FXML
    private void handleStartGame(ActionEvent event) throws IOException {
        startGame();

    }

    public void startGame() throws IOException {
        //start up the actual game/game logic
        Game game = new Game();

        Stage stage;
        Parent root;
        stage = (Stage) startGameButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/mainView.fxml"));
        root = loader.load();
        //root = FXMLLoader.load(getClass().getResource("/resources/sample.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getScene().setOnKeyPressed(event -> {
            MainViewInputRecognizer controller = loader.getController();
            try {
                controller.keyListener(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        stage.show();
    }

    @FXML
    public void keyListener(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            startGame();
        }
        //keyCode = event.getCode();
        //System.out.println(keyCode.toString());
        //keyPressed.setText("Key Pressed: " + keyCode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
