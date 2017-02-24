package controller.keyboardinputhandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by LesliesLaptop on 2/23/17.
 */
public class MainViewInputRecognizer implements Initializable {

    @FXML
    MenuBar mainMenuBar;

    @FXML
    public void handleReturnToMainMenu(ActionEvent actionEvent) throws IOException {
        returnToMainMenu();
    }

    public void returnToMainMenu() throws IOException {
        // TODO: properly end all game logic in Game class when returning to main menu
        Stage stage;
        Parent root;
        stage = (Stage) mainMenuBar.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/welcomeView.fxml"));
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

    //quit the entire game application
    @FXML
    public void handleQuitGame(ActionEvent event) {
        quitGame();
    }

    public void quitGame() {
        getStage().close();
    }

    @FXML
    public void keyListener(KeyEvent event) throws IOException {
        //TODO: add key menu shortcuts and command control recognition
    }

    @FXML
    private Stage getStage() {
       Stage stage = (Stage) mainMenuBar.getScene().getWindow();
       return stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
