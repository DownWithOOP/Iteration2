import controller.keyboardinputhandler.WelcomeViewInputRecognizer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Konrad on 2/17/2017.
 */
public class RunGame extends  Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/welcomeView.fxml"));
        Pane root = loader.load();
        primaryStage.setTitle("Space Cats!");

        //TODO Shouldn't we construct a scene, tell it to set the onKeyPressed, and then call primaryStage.setScene() ?
        primaryStage.setScene(new Scene(root, 600, 453));
        primaryStage.getScene().setOnKeyPressed(event -> {
            WelcomeViewInputRecognizer controller = loader.getController();
            try {
                controller.keyListener(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Create canvas that we need to draw map and everything else on.
        final Canvas areaViewport = new Canvas(250,250);
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


    public static void main(String args[]){
        // entry point of the application
        // lets create the game object
        System.out.println("Game has started running");
        //Game game = new Game();
        launch(args); // launch the GUI
    }
}
