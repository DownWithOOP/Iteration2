package model;

import controller.ControllerDispatch;
import controller.ControllerManager;

import java.util.Scanner;

/**
 * Created by Konrad on 2/17/2017.
 */
public class Game {

    private ControllerManager controllerManager;
    private ControllerDispatch controllerDispatch;

    public Game(){
        //TODO:change hardcoded player number
        this.controllerDispatch = new ControllerDispatch(5);
        this.controllerManager = new ControllerManager(this.controllerDispatch);

        // now lets run the game
        runGameLoop();
    }



    private void runGameLoop(){
        // this is going to be the main game loop, constantly checking for updates and such
        Scanner input = new Scanner(System.in);
        while(true){
            String test = input.nextLine();

            System.out.println(test);



        }

    }


}
