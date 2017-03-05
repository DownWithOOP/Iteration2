import javafx.animation.AnimationTimer;

/**
 * Created by Konrad on 2/26/2017.
 */
public class GameLoop {

    private AnimationTimer animationTimer;

    public GameLoop(){
        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                //TODO tell the game to update/render here
            }
        };

    }

    public void start() {
        animationTimer.start();
    }
}
