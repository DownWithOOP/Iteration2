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
public class IntroVideo {

    private static MediaPlayer mediaplayer;
    private static boolean WelcomeViewLoaded = false;

    public IntroVideo(){}


    public static void firstVideo(Stage primaryStage, Media videoFile, Media videoFile2, FXMLLoader welcomeLoader, String cssSheet){

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
                    mediaplayer.stop();
                    // matches, we want to end the video and jump to the next one
                    mediaplayer = new MediaPlayer(videoFile2);
                    mediaplayer.setVolume(0.1);
                    MediaView mediaView = new MediaView(mediaplayer);
                    DoubleProperty mvw = mediaView.fitWidthProperty();
                    DoubleProperty mvh = mediaView.fitHeightProperty();
                    mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                    mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
                    mediaView.setPreserveRatio(false);
                    Scene scene = new Scene(new Group(mediaView), 1000, 1000);
                    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                              public void handle(KeyEvent ke) {
                                                  if (ke.getCode() == KeyCode.SPACE) {
                                                        mediaplayer.stop();
                                                      loadWelcome(primaryStage, welcomeLoader, cssSheet);
                                                  }
                                              }
                                          });
                    mediaplayer.setOnEndOfMedia(new Runnable() {
                        public void run()
                        {
                            loadWelcome(primaryStage, welcomeLoader, cssSheet);
                        }});
                    primaryStage.setScene(scene);
                    primaryStage.setFullScreenExitHint("");
                    primaryStage.setFullScreen(true);
                    mediaplayer.play();
                    primaryStage.show();
                }
            }
        });
        // event handler for mediaPlayer, when the first videoEnds, we want to transition to the second video
        mediaplayer.setOnEndOfMedia(new Runnable() {
            public void run()
            {
                mediaplayer = new MediaPlayer(videoFile2);
                mediaplayer.setVolume(0.1);
                MediaView mediaView = new MediaView(mediaplayer);
                DoubleProperty mvw = mediaView.fitWidthProperty();
                DoubleProperty mvh = mediaView.fitHeightProperty();
                mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
                mediaView.setPreserveRatio(false);
                Scene scene = new Scene(new Group(mediaView), 1000, 1000);
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent ke) {
                        if (ke.getCode() == KeyCode.SPACE) {
                            mediaplayer.stop();
                            loadWelcome(primaryStage, welcomeLoader, cssSheet);
                        }
                    }
                });
                mediaplayer.setOnEndOfMedia(new Runnable() {
                    public void run()
                    {
                        loadWelcome(primaryStage, welcomeLoader, cssSheet);
                    }});
                primaryStage.setScene(scene);
                primaryStage.setFullScreenExitHint("");
                primaryStage.setFullScreen(true);
                mediaplayer.play();
                primaryStage.show();
            }
        });
        mediaplayer.play();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // called when we want to transition to the welcomeViewController
    public static void loadWelcome(Stage primaryStage, FXMLLoader welcomeLoader, String cssSheet){
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

            GameLoop timer = new GameLoop();
            timer.start();
            primaryStage.show();

        }

    }


}
