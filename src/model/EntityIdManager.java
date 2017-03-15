package model;

import java.util.ArrayList;

/**
 * Created by Jonathen on 3/14/2017.
 */
public class EntityIdManager {

    private ArrayList<Integer> armyIds = new ArrayList<>();

    public EntityIdManager() {
        initializeArmyIds();
    }

    private void initializeArmyIds() {
        for (int i=0; i < 10; i++) {
            armyIds.add(i);
        }
    }

    public String getArmyId() {
        String idToReturn = String.valueOf(armyIds.get(0));
        armyIds.remove(0);
        return idToReturn;
    }

    public void removeArmyId(String idToRemove) {
        int idxAndId = Integer.parseInt(idToRemove);
        //Add id back if it is not in the id list
        if (armyIds.indexOf(idxAndId) != -1) {
           return;
        }
        armyIds.add(idxAndId, idxAndId);
    }


}
