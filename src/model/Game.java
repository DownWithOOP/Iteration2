package model;

import controller.ControllerDispatch;
import controller.ControllerManager;
import controller.Observers.ControllerStatusObserver;
import controller.Observers.MapDisplayObserver;
import controller.Observers.PlayerObservator;
import javafx.stage.Stage;
import utilities.ObserverInterfaces.PlayerObserver;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

import java.io.IOException;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Game {

    private ControllerManager controllerManager;
    private ControllerDispatch controllerDispatch;
    private MapDisplayObserver mapDisplayObserver;
    private UnitObserver unitObserver;
    private StructureObserver structureObserver;
    private StatusObserver statusObserver;
    private PlayerObservator playerObserver;

    public Game(Stage primaryStage) throws IOException {

        //TODO:change hardcoded player number
        int numberOfPlayers = 2;

        this.mapDisplayObserver = new MapDisplayObserver(numberOfPlayers);
        this.unitObserver = new controller.Observers.UnitObserver();
        this.structureObserver = new controller.Observers.StructureObserver();
        this.statusObserver = new ControllerStatusObserver();
        this.playerObserver = new PlayerObservator();
        this.controllerDispatch = new ControllerDispatch(numberOfPlayers, mapDisplayObserver, unitObserver, structureObserver, statusObserver, playerObserver);
        this.controllerManager = new ControllerManager(this.controllerDispatch, primaryStage, mapDisplayObserver, unitObserver, structureObserver, statusObserver, playerObserver);

    }




}
