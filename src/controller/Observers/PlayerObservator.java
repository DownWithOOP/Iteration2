package controller.Observers;

import java.util.HashMap;

/**
 * Created by Konrad on 3/15/2017.
 */
public class PlayerObservator implements utilities.ObserverInterfaces.PlayerObserver{

    private HashMap keymap = new HashMap();
    @Override
    public void update(HashMap keyMap) {

        System.out.println("SIZE IS:  " + keymap.size());
        this.keymap = keyMap;
    }
    public HashMap share(){
        return this.keymap;
    }
}
