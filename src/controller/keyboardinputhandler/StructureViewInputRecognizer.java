package controller.keyboardinputhandler;

import controller.SwitchControllerRelay;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 2/25/2017.
 */
public class StructureViewInputRecognizer  implements Initializable {

    private SwitchControllerRelay switchControllerRelay;


    public void handleChangeToMainView(ActionEvent actionEvent) throws  IOException{
        this.switchControllerRelay.changeToMain();
    }

    public void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay){
        this.switchControllerRelay = switchControllerRelay;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void keyListener(KeyEvent event) throws IOException {
        //TODO: add key menu shortcuts and command control recognition

    }


}
