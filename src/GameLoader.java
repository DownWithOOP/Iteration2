import controller.WelcomeViewController.WelcomeViewIndependentController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Konrad on 2/26/2017.
 */
public class GameLoader {

    private static MediaPlayer mediaplayer;
    private static boolean WelcomeViewLoaded = false;

    public GameLoader(){}

    // called when we want to transition to the welcomeViewController
    public static void load(Stage primaryStage, FXMLLoader welcomeLoader, String cssSheet){
        if(WelcomeViewLoaded == false){
            WelcomeViewLoaded = true;
            Pane root = null;
            try {
                root = welcomeLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root,1221,726);

            scene.getStylesheets().addAll(cssSheet);
            primaryStage.setScene(scene);
            primaryStage.getScene().setOnKeyPressed(event -> {

                WelcomeViewIndependentController controller = welcomeLoader.getController();
                try {
                    controller.keyListener(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            primaryStage.show();

        }

    }


}
