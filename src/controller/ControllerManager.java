package controller;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerManager {

    // TODO make a map or something to keep track of these
    private MainViewController mainViewController;
    private WelcomeViewController welcomeViewController;

    // we need to keep track of the currently active controller
    private Controller activeController;

    public ControllerManager(ControllerDispatch controllerDispatch){
        // intialize
        mainViewController = new MainViewController(controllerDispatch);
        welcomeViewController = new WelcomeViewController(controllerDispatch);

        // when the game is first initialized, we start at the welcomeView controller
        this.activeController = welcomeViewController;
    }



}
