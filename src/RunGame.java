import controller.WelcomeViewController.WelcomeViewIndependentController;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.media.MediaPlayer;

/**
 * Created by Konrad on 2/17/2017.
 */


public class RunGame extends  Application{

    private boolean WelcomeViewLoaded = false;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // we set the title of the stage
        primaryStage.setTitle("Space Cats!");
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);

        // we load the 2 videos here, along with the CSS sheet and the welcome View, this must be done because of annoying static issues
        Media videoFile = new Media(getClass().getResource("resources/Videos/Activision.mp4").toExternalForm());
        Media videoFile2 = new Media(getClass().getResource("resources/Videos/Nvidia.mp4").toExternalForm());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/WelcomeView.fxml"));
        String cssSheet = this.getClass().getResource("resources/style.css").toExternalForm();

        // call method to play the intro videos and transition to welcomeView
        IntroVideo.firstVideo(primaryStage, videoFile, videoFile2, loader, cssSheet);
    }
    // Starting point of the game
    public static void main(String args[]){
        // entry point of the application
        // lets create the game object
        System.out.println("Game has started running");
        launch(args); // launch the GUI
        System.out.println("we get here");
    }
}
