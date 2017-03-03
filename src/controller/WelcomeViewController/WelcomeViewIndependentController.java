package controller.WelcomeViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jordi on 2/21/2017.
 */
public class WelcomeViewIndependentController implements Initializable {

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
    @FXML
    private void exitGame(ActionEvent event) throws  IOException {
        System.exit(0);
    }

    public void startGame() throws IOException {
        //start up the actual game/game logic
        Game game = new Game((Stage) startGameButton.getScene().getWindow());
    }

    @FXML
    public void keyListener(KeyEvent event) throws IOException {
        /*
        if (event.getCode() == KeyCode.ENTER) {
            startGame();
        }
        */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
