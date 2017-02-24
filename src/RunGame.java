import controller.keyboardinputhandler.WelcomeViewInputRecognizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Konrad on 2/17/2017.
 */
public class RunGame extends  Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/welcomeView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Space Cats!");
        primaryStage.setScene(new Scene(root, 600, 453));
        primaryStage.getScene().setOnKeyPressed(event -> {
            WelcomeViewInputRecognizer controller = loader.getController();
            try {
                controller.keyListener(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        primaryStage.show();
    }


    public static void main(String args[]){
        // entry point of the application
        // lets create the game object
        System.out.println("Game has started running");
        //Game game = new Game();
        launch(args); // launch the GUI
    }
}
