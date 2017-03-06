package model;

import controller.ControllerDispatch;
import controller.ControllerManager;
import controller.Observers.MapObserver;
import javafx.stage.Stage;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

import java.io.IOException;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Game {

    private ControllerManager controllerManager;
    private ControllerDispatch controllerDispatch;
    private MapObserver mapObserver;
    private UnitObserver unitObserver;
    private StructureObserver structureObserver;

    public Game(Stage primaryStage) throws IOException {

        //TODO:change hardcoded player number
        this.mapObserver = new MapObserver();
        this.unitObserver = new controller.Observers.UnitObserver();
        this.structureObserver = new controller.Observers.StructureObserver();
        this.controllerDispatch = new ControllerDispatch(2, mapObserver, unitObserver, structureObserver);
        this.controllerManager = new ControllerManager(this.controllerDispatch, primaryStage, mapObserver, unitObserver, structureObserver);

    }




}
