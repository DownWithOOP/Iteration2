package model;

import controller.ControllerDispatch;
import controller.ControllerManager;
import controller.Observers.MapObserver;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Game {

    private ControllerManager controllerManager;
    private ControllerDispatch controllerDispatch;
    private MapObserver mapObserver;

    public Game(Stage primaryStage) throws IOException {

        //TODO:change hardcoded player number
        this.mapObserver = new MapObserver();
        this.controllerDispatch = new ControllerDispatch(2, mapObserver);
        this.controllerManager = new ControllerManager(this.controllerDispatch, primaryStage, mapObserver);

    }




}
