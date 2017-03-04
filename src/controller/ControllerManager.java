package controller;

import controller.Observers.MainViewObserver;
import controller.ingamecontrollertypes.ControllerType;
import controller.ingamecontrollertypes.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerManager {

    HashMap<ControllerType, Controller> controllerMap = new HashMap<>();

    // we need to keep track of the currently active controller
    //TODO: make this private
    public Controller activeController;
    private Stage primaryStage; // the stage is going to be kept track of here, whenever we are going to want to channge controllers,
    // it will be done here
    private SwitchControllerRelay switchControllerRelay;
    private ControllerDispatch controllerDispatch;
    private MainViewObserver mainViewObserver;

    public ControllerManager(ControllerDispatch controllerDispatch, Stage primaryStage, MainViewObserver mainViewObserver) throws IOException {

        // primary stage that is essentially the window
        this.primaryStage = primaryStage;
        this.activeController = this.controllerMap.get(ControllerType.welcomeViewController);
        this.mainViewObserver = mainViewObserver;

        // used for communication between the inputReconginzers and the controllerManager when a controller needs to be switched
        switchControllerRelay = new SwitchControllerRelay(this);

        this.controllerDispatch = controllerDispatch;

        // when the controller manager is initialized, we start up the mainView
        changeToMainView();
    }

    public void switchControllers(ControllerType controllerType) {
        activeController = controllerMap.get(controllerType);
    }


    // this method will be called to handle loading in the mainView
    public void changeToMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/mainView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Controller inputController = loader.getController();
        inputController.takeInSwitchControllerRelay(switchControllerRelay);
        inputController.enableKeyboardInput();
        inputController.setDispatch(controllerDispatch);
        inputController.takeInObserver(mainViewObserver);
        MainViewController temp =  (MainViewController)inputController; // Sketchy but we have to downCast to the MainView Controller type
        temp.drawOnCanvas(); // at this point everything is guaranteed to have loaded in and we can display the map
        primaryStage.show();
    }

    // this method will be used to handle loading in the structureView
    public void changeToStructureView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/structureView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Controller inputController = loader.getController();
        inputController.takeInSwitchControllerRelay(switchControllerRelay);
        inputController.enableKeyboardInput();
        inputController.setDispatch(controllerDispatch);
        primaryStage.show();
    }


    // this method will be used to handle loading in the unitView
    public void changeToUnitView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/unitView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Controller inputController = loader.getController();
        inputController.takeInSwitchControllerRelay(switchControllerRelay);
        inputController.enableKeyboardInput();
        inputController.setDispatch(controllerDispatch);
        primaryStage.show();
    }

    public void changeToTechTreeView() throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/techTree.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Controller inputController = loader.getController();
        inputController.takeInSwitchControllerRelay(switchControllerRelay);
        inputController.enableKeyboardInput();
        inputController.setDispatch(controllerDispatch);
        primaryStage.show();
    }

}
