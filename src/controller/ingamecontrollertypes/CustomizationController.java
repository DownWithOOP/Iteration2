package controller.ingamecontrollertypes;

import controller.Controller;
import controller.SwitchControllerRelay;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import view.utilities.FileWriter;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Konrad on 3/15/2017.
 */
public class CustomizationController extends Controller {

    private HashMap<KeyCode,String> playerHashMap;
    private HashMap<String,KeyCode> reference;
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
    private void importData(){
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(this.keyFive.getScene().getWindow());
        if (file != null) {
            try{
                playerHashMap.clear();
                Scanner input = new Scanner(file);
                while(input.hasNextLine()){
                    String line1 = input.nextLine();
                    String line2 = input.nextLine();
                    KeyCode temp = (KeyCode)reference.get(line2);
                    playerHashMap.put(temp, line1);

                    if(line1.equals("NORTH")){
                        keyOne.setText(" : " + temp);
                    }
                    if(line1.equals("SOUTH")){
                        keyTwo.setText(" : " + temp);
                    }
                    if(line1.equals("NE")){
                        keyThree.setText(" : " + temp);
                    }
                    if(line1.equals("SE")){
                        keyFour.setText(" : " + temp);
                    }
                    if(line1.equals("NW")){
                        keyFive.setText(" : " + temp);
                    }
                    if(line1.equals("SW")){
                        keySix.setText(" : " + temp);
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
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

        // initialize map
        this.reference = new HashMap<>();

        // numpad
        reference.put("NUMPAD1", KeyCode.NUMPAD1);
        reference.put("NUMPAD2", KeyCode.NUMPAD2);
        reference.put("NUMPAD3", KeyCode.NUMPAD3);
        reference.put("NUMPAD4", KeyCode.NUMPAD4);
        reference.put("NUMPAD5", KeyCode.NUMPAD5);
        reference.put("NUMPAD6", KeyCode.NUMPAD6);
        reference.put("NUMPAD7", KeyCode.NUMPAD7);
        reference.put("NUMPAD8",KeyCode.NUMPAD8);
        reference.put("NUMPAD9",KeyCode.NUMPAD9);

        // digits
        reference.put("DIGIT0",KeyCode.DIGIT0);
        reference.put("DIGIT1",KeyCode.DIGIT1);
        reference.put("DIGIT2",KeyCode.DIGIT2);
        reference.put("DIGIT3",KeyCode.DIGIT3);
        reference.put("DIGIT4",KeyCode.DIGIT4);
        reference.put("DIGIT5",KeyCode.DIGIT5);
        reference.put("DIGIT6",KeyCode.DIGIT6);
        reference.put("DIGIT7",KeyCode.DIGIT7);
        reference.put("DIGIT8",KeyCode.DIGIT8);
        reference.put("DIGIT9",KeyCode.DIGIT9);

        // all numbers
        reference.put("a",KeyCode.A);
        reference.put("A",KeyCode.A);
        reference.put("b",KeyCode.B);
        reference.put("B",KeyCode.B);
        reference.put("c",KeyCode.C);
        reference.put("C",KeyCode.C);
        reference.put("d",KeyCode.D);
        reference.put("D",KeyCode.D);
        reference.put("e",KeyCode.E);
        reference.put("E",KeyCode.E);
        reference.put("f",KeyCode.F);
        reference.put("F",KeyCode.F);
        reference.put("g",KeyCode.G);
        reference.put("G",KeyCode.G);
        reference.put("h",KeyCode.H);
        reference.put("H",KeyCode.H);
        reference.put("I",KeyCode.I);
        reference.put("i",KeyCode.I);
        reference.put("J",KeyCode.J);
        reference.put("j",KeyCode.J);
        reference.put("K",KeyCode.K);
        reference.put("k",KeyCode.K);
        reference.put("l",KeyCode.L);
        reference.put("L",KeyCode.L);
        reference.put("M",KeyCode.M);
        reference.put("m",KeyCode.M);
        reference.put("n",KeyCode.N);
        reference.put("N",KeyCode.N);
        reference.put("o",KeyCode.O);
        reference.put("O",KeyCode.O);
        reference.put("p",KeyCode.P);
        reference.put("P",KeyCode.P);
        reference.put("Q",KeyCode.Q);
        reference.put("q",KeyCode.Q);
        reference.put("R",KeyCode.R);
        reference.put("r",KeyCode.R);
        reference.put("S",KeyCode.S);
        reference.put("s",KeyCode.S);
        reference.put("T",KeyCode.T);
        reference.put("t",KeyCode.T);
        reference.put("U",KeyCode.U);
        reference.put("u",KeyCode.U);
        reference.put("V",KeyCode.V);
        reference.put("v",KeyCode.V);
        reference.put("W",KeyCode.W);
        reference.put("w",KeyCode.W);
        reference.put("X",KeyCode.X);
        reference.put("x",KeyCode.X);
        reference.put("Y",KeyCode.Y);
        reference.put("y",KeyCode.Y);
        reference.put("Z",KeyCode.Z);
        reference.put("z",KeyCode.Z);

        //
        reference.put("SPACE",KeyCode.SPACE);
        reference.put("BACK_SPACE",KeyCode.BACK_SPACE);
        reference.put("ENTER",KeyCode.ENTER);
        reference.put("SHIFT",KeyCode.SHIFT);
        reference.put("UP",KeyCode.UP);
        reference.put("DOWN",KeyCode.DOWN);
        reference.put("LEFT",KeyCode.LEFT);
        reference.put("RIGHT",KeyCode.RIGHT);



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
