package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.utilities.FileWriter;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by Konrad on 3/15/2017.
 */
public class CustomizationController extends Controller {

    private HashMap<KeyCode,String> playerHashMap;
    private FileWriter fileWriter;


    @FXML
    TextField keyOne;
    @FXML
    TextField keyTwo;
    @FXML
    TextField keyThree;
    @FXML
    TextField keyFour;
    @FXML
    TextField keyFive;
    @FXML
    TextField keySix;



    @Override
    protected void takeInSwitchControllerRelay(SwitchControllerRelay switchControllerRelay) {

    }

    @Override
    protected void enableKeyboardInput() {

    }
    protected void getHashMap(HashMap hashMap){
        this.playerHashMap = hashMap;

        Iterator it = playerHashMap.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            if(pair.getValue().equals("NORTH")){
                keyOne.setText(pair.getKey().toString());
            } else if(pair.getValue().equals("SOUTH")){
                keyTwo.setText(pair.getKey().toString());
            } else if(pair.getValue().equals("NW")){
                keyThree.setText(pair.getKey().toString());
            } else if(pair.getValue().equals("SW")){
                keyFour.setText(pair.getKey().toString());
            } else if(pair.getValue().equals("SE")){
                keyFive.setText(pair.getKey().toString());
            } else if(pair.getValue().equals("NE")){
                keySix.setText(pair.getKey().toString());
            }
        }
    }

    @FXML
    private void exportControls(){
        this.fileWriter.initialize();
        Iterator it = playerHashMap.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            if(pair.getValue().equals("NORTH")){
                this.fileWriter.writeData("NORTH\n" + pair.getKey() +"\n");
            } else if(pair.getValue().equals("SOUTH")){
                this.fileWriter.writeData("SOUTH\n" + pair.getKey() +"\n");
            } else if(pair.getValue().equals("NW")){
                this.fileWriter.writeData("NW\n" + pair.getKey() +"\n");
            } else if(pair.getValue().equals("SW")){
                this.fileWriter.writeData("SW\n" + pair.getKey() +"\n");
            } else if(pair.getValue().equals("SE")){
                this.fileWriter.writeData("SE\n" + pair.getKey() +"\n");
            } else if(pair.getValue().equals("NE")){
                this.fileWriter.writeData("NE\n" + pair.getKey() +"\n");
            }
        }
        this.fileWriter.stopWriting();
    }

    @Override
    protected void render() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fileWriter = new FileWriter();

        this.keyOne.addEventFilter(KeyEvent.KEY_PRESSED, event ->
        {
            keyOne.setText(" : " + event.getCode());
            KeyCode answer = null;
            for (KeyCode o : playerHashMap.keySet()) {
                if (playerHashMap.get(o).equals("NORTH")) {
                    answer = o;
                }
            }
            playerHashMap.remove(answer);
            playerHashMap.put(event.getCode(),"NORTH");
        });

        this.keyTwo.addEventFilter(KeyEvent.KEY_PRESSED, event ->
        {
            keyTwo.setText(" : " + event.getCode());
            KeyCode answer = null;
            for (KeyCode o : playerHashMap.keySet()) {
                if (playerHashMap.get(o).equals("SOUTH")) {
                    answer = o;
                }
            }
            playerHashMap.remove(answer);
            playerHashMap.put(event.getCode(),"SOUTH");
        });

        this.keyThree.addEventFilter(KeyEvent.KEY_PRESSED, event ->
        {
            keyThree.setText(" : " + event.getCode());
            KeyCode answer = null;
            for (KeyCode o : playerHashMap.keySet()) {
                if (playerHashMap.get(o).equals("NE")) {
                    answer = o;
                }
            }
            playerHashMap.remove(answer);
            playerHashMap.put(event.getCode(),"NE");
        });

        this.keyFour.addEventFilter(KeyEvent.KEY_PRESSED, event ->
        {
            keyFour.setText(" : " + event.getCode());
            KeyCode answer = null;
            for (KeyCode o : playerHashMap.keySet()) {
                if (playerHashMap.get(o).equals("SE")) {
                    answer = o;
                }
            }
            playerHashMap.remove(answer);
            playerHashMap.put(event.getCode(),"SE");
        });

        this.keyFive.addEventFilter(KeyEvent.KEY_PRESSED, event ->
        {

            keyFive.setText(" : " + event.getCode());
            KeyCode answer = null;
            for (KeyCode o : playerHashMap.keySet()) {
                if (playerHashMap.get(o).equals("NW")) {
                    answer = o;
                }
            }
            playerHashMap.remove(answer);
            playerHashMap.put(event.getCode(),"NW");
        });

        this.keySix.addEventFilter(KeyEvent.KEY_PRESSED, event ->
        {
            keySix.setText(" :" + event.getCode());
            KeyCode answer = null;
            for (KeyCode o : playerHashMap.keySet()) {
                if (playerHashMap.get(o).equals("SW")) {
                    answer = o;
                }
            }
            playerHashMap.remove(answer);
            playerHashMap.put(event.getCode(),"SW");
        });
    }
}
