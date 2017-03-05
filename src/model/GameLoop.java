package model;

import controller.Controller;
import controller.ControllerManager;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

/**
 * Created by Konrad on 2/26/2017.
 */
public class GameLoop {


    private Controller activeController;
    private AnimationTimer animationTimer;

    public GameLoop(ControllerManager controllerManager){
        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            //TODO find a better way to do all of this

            controllerManager.renderCurrentView();
            }
        };

    }

    public void start() {
        animationTimer.start();
    }
}
