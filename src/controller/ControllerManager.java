package controller;

import controller.controllerTypes.ControllerType;
import controller.controllerTypes.MainViewController;
import controller.controllerTypes.WelcomeViewController;
import controller.keyboardinputhandler.MainViewInputRecognizer;
import controller.keyboardinputhandler.StructureViewInputRecognizer;
import controller.keyboardinputhandler.UnitViewInputRecognizer;
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

    public ControllerManager(ControllerDispatch controllerDispatch, Stage primaryStage) throws IOException {

        // primary stage that is essentially the window
        this.primaryStage = primaryStage;
        initializeControllers(controllerDispatch);
        this.activeController = this.controllerMap.get(ControllerType.welcomeViewController);

        // used for communication between the inputReconginzers and the controllerManager when a controller needs to be switched
        switchControllerRelay = new SwitchControllerRelay(this);

        // when the controller manager is initialized, we start up the mainView
        changeToMainView();
    }

    private void initializeControllers(ControllerDispatch controllerDispatch) {
        controllerMap.put(ControllerType.mainViewController, new MainViewController(controllerDispatch, this));
        controllerMap.put(ControllerType.welcomeViewController, new WelcomeViewController(controllerDispatch, this));

    }

    public void switchControllers(ControllerType controllerType) {
        activeController = controllerMap.get(controllerType);
        activeController.resumeController();
    }


    // this method will be called to handle loading in the mainView
    public void changeToMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/mainView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        MainViewInputRecognizer inputController = loader.getController();
        inputController.takeInSwitchControllerRelay(switchControllerRelay);
        primaryStage.show();
    }

    // this method will be used to handle loading in the structureView
    public void changeToStructureView() throws IOException {
        System.out.println("called");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/structureView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        StructureViewInputRecognizer inputController = loader.getController();
        System.out.println("this is the inputController" +loader.getController()    );
        inputController.takeInSwitchControllerRelay(switchControllerRelay);
        primaryStage.show();
    }


    // this method will be used to handle loading in the unitView
    public void changeToUnitView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/unitView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        UnitViewInputRecognizer inputController = loader.getController();
        inputController.takeInSwitchControllerRelay(switchControllerRelay);
        primaryStage.show();
    }







}
