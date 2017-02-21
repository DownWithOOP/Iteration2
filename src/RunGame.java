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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String args[]){
        // entry point of the appliction
        // lets create the game object
        System.out.println("Game has started running");
        Game game = new Game();
//        launch(args); // launch the GUI
    }
}
