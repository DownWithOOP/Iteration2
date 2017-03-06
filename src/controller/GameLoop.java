package controller;

import javafx.animation.AnimationTimer;

/**
 * Created by Konrad on 2/26/2017.
 */
public class GameLoop {

    private AnimationTimer animationTimer;
    private Controller activeController;

    public GameLoop(Controller activeController){
        this.activeController = activeController;
        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if(activeController == null){
                   System.out.println("NULL LOOP");
                } else {
                    callRender();
                }
            }
        };
    }
    private void callRender(){
        this.activeController.render();
    }

    public void updateController(Controller newactiveController){
        this.activeController = newactiveController;
    }

    public void start() {
        animationTimer.start();
    }
}
