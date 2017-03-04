package model;

import controller.ControllerDispatch;
import controller.ControllerManager;
import controller.Observers.MainViewObserver;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Game {

    private ControllerManager controllerManager;
    private ControllerDispatch controllerDispatch;
    private MainViewObserver mainViewObserver;

    public Game(Stage primaryStage) throws IOException {

        //TODO:change hardcoded player number
        this.mainViewObserver = new MainViewObserver();
        this.controllerDispatch = new ControllerDispatch(2, mainViewObserver);
        this.controllerManager = new ControllerManager(this.controllerDispatch, primaryStage, mainViewObserver);

    }




}
