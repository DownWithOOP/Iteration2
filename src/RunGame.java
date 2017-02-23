import controller.keyboardinputhandler.KeyboardInputHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import model.Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Konrad on 2/17/2017.
 */
public class RunGame extends  Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.getScene().setOnKeyPressed(event -> {
            KeyboardInputHandler controller = loader.getController();
            controller.keyListener(event);
        });
        primaryStage.show();
    }


    public static void main(String args[]){
        // entry point of the appliction
        // lets create the game object
        System.out.println("Game has started running");
        launch(args); // launch the GUI
        //Game game = new Game();
    }
}
