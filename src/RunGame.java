import controller.WelcomeViewController.WelcomeViewIndependentController;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Created by Konrad on 2/17/2017.
 */


public class RunGame extends  Application{

    // mediaplayer that is used for the intro sequences
    MediaPlayer mediaplayer;
    private boolean WelcomeViewLoaded = false;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // we set the title of the stage
        primaryStage.setTitle("Space Cats!");
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        // ---- here we send the primaryStage to the controllerManager which will manage everything


        // get the scene for playing the first video
        primaryStage.setScene(firstVideo(primaryStage));
        // play the first video
        mediaplayer.play();
        primaryStage.show();

    }

    // Starting point of the game
    public static void main(String args[]){
        // entry point of the application
        // lets create the game object
        System.out.println("Game has started running");
        launch(args); // launch the GUI
        System.out.println("we get here");
    }


    // called to load in the first video
    public Scene firstVideo(Stage primaryStage){
        Media videoFile = new Media(getClass().getResource("resources/Videos/Activision.mp4").toExternalForm());

        mediaplayer = new MediaPlayer(videoFile);
        mediaplayer.setVolume(0.1);

        MediaView mediaView = new MediaView(mediaplayer);

        // to make videoPlayArea fullscreen
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();

        // force video to fit to resolution
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));


        mediaView.setPreserveRatio(false);

        // we set the first video, and then we play it
        Scene scene = new Scene(new Group(mediaView), 300, 300);

        // even handler for this scene, if we press space,we want the video to end
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(ke.getCode() == KeyCode.SPACE){
                    // matches, we want to end the video and jump to the next one
                    swapToSecond(primaryStage);
                }
            }
        });
        // event handler for mediaPlayer, when the first videoEnds, we want to transition to the second video
        mediaplayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run()
            {
                swapToSecond(primaryStage);
            }
        });
        return scene;
    }
    public void swapToSecond(Stage primaryStage){
        mediaplayer.stop();
        primaryStage.setScene(secondVideo(primaryStage));
        primaryStage.setFullScreen(true);
        mediaplayer.play();
        primaryStage.show();
    }

    // called to load in the second video
    public Scene secondVideo(Stage primaryStage){
        Media videoFile = new Media(getClass().getResource("resources/Videos/Nvidia.mp4").toExternalForm());
        mediaplayer = new MediaPlayer(videoFile);
        mediaplayer.setVolume(0.1);


        MediaView mediaView = new MediaView(mediaplayer);

        // to make videoPlayArea fullscreen
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();

        // force video to fit to resolution
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(false);

        Scene scene = new Scene(new Group(mediaView), 300, 300);

        // event handler to end video early if you want to
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(ke.getCode() == KeyCode.SPACE){
                    // matches, we want to end the video and jump to the next one
                    LoadWelcome(primaryStage);
                }
            }
        });

        // event handler for the mediaPlayer, when the second videoEnds, we want to transition to the welcomeView
        mediaplayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run()
            {
                LoadWelcome(primaryStage);
            }
        } )  ;
        return scene;
    }

    // called when we want to transition to the welcomeViewController
    public void LoadWelcome(Stage primaryStage){

            if(this.WelcomeViewLoaded == false){
                this.WelcomeViewLoaded = true;

                //TODO Shouldn't we construct a scene, tell it to set the onKeyPressed, and then call primaryStage.setScene() ?
                FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/welcomeView.fxml"));
                Pane root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root,600,600);
                scene.getStylesheets().addAll(this.getClass().getResource("resources/style.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.getScene().setOnKeyPressed(event -> {
                    WelcomeViewIndependentController controller = loader.getController();
                    try {
                        controller.keyListener(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                //Create canvas that we need to draw map and everything else on.
                final Canvas areaViewport = new Canvas(600,600);
                GraphicsContext gc = areaViewport.getGraphicsContext2D();

                //TODO how do we tell root where to put the area viewport?
                root.getChildren().add(areaViewport);


                new AnimationTimer()
                {
                    public void handle(long currentNanoTime)
                    {
                        //TODO tell the game to update/render here
                    }
                }.start();
                primaryStage.show();
            }


    }
}
