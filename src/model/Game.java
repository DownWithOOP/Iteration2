package model;

import controller.ControllerDispatch;
import controller.ControllerManager;
import controller.controllerTypes.ControllerType;
import controller.controllerTypes.MainViewController;

import java.util.Scanner;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Game {

    private ControllerManager controllerManager;
    private ControllerDispatch controllerDispatch;

    public Game(){
        //TODO:change hardcoded player number
        this.controllerDispatch = new ControllerDispatch(2);
        this.controllerManager = new ControllerManager(this.controllerDispatch);

        // now lets run the game
        System.out.println("Game Loop Started");
        runGameLoop();
    }



    private void runGameLoop(){
        // this is going to be the main game loop, constantly checking for updates and such

        //TODO: this is used for testing the endTurn function, delete this
        Scanner input = new Scanner(System.in);
        String test = input.nextLine();
        System.out.println(test);
        controllerManager.switchControllers(ControllerType.mainViewController);
        ((MainViewController)controllerManager.activeController).transferAction();
        ((MainViewController)controllerManager.activeController).transferAction();

    }


}
