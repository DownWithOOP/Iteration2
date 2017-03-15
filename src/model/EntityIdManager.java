package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jonathen on 3/14/2017.
 */
public class EntityIdManager {

    private ArrayList<Integer> armyIds = new ArrayList<>();

    private ArrayList<Integer> colonistIds = new ArrayList<>();
    private ArrayList<Integer> explorerIds = new ArrayList<>();
    private ArrayList<Integer> meleeIds = new ArrayList<>();
    private ArrayList<Integer> rangedIds = new ArrayList<>();

    private ArrayList<Integer> workerIds = new ArrayList<>();

    private ArrayList<Integer> capitalIds = new ArrayList<>();
    private ArrayList<Integer> farmIds = new ArrayList<>();
    private ArrayList<Integer> fortIds = new ArrayList<>();
    private ArrayList<Integer> mineIds = new ArrayList<>();
    private ArrayList<Integer> observationTowerIds = new ArrayList<>();
    private ArrayList<Integer> powerPlantIds = new ArrayList<>();
    private ArrayList<Integer> universityIds = new ArrayList<>();

    private static EntityIdManager entityIdManager = new EntityIdManager();

    private EntityIdManager() {
        initializeArmyIds();
        initializeUnitIds();
        initializeWorkerIds();
        initializeStructureIds();
    }

    private void initializeWorkerIds() {
        for (int i = 0; i < 100; i++) {
            workerIds.add(i);
        }
    }

    private void initializeUnitIds() {
        for (int i=0; i < 10; i++) {
            colonistIds.add(i);
            explorerIds.add(i);
            meleeIds.add(i);
            rangedIds.add(i);
        }
    }

    private void initializeStructureIds() {
        for (int i=0; i< 10; i++) {
            capitalIds.add(i);
            farmIds.add(i);
            fortIds.add(i);
            mineIds.add(i);
            observationTowerIds.add(i);
            powerPlantIds.add(i);
            universityIds.add(i);

        }
    }

    public static EntityIdManager getInstance() {
        return entityIdManager;
    }

    private void initializeArmyIds() {
        for (int i=0; i < 10; i++) {
            armyIds.add(i);
        }
    }

    private String getId(ArrayList<Integer> ids) {
        String idToReturn = String.valueOf(ids.get(0));
        ids.remove(0);
        return idToReturn;
    }

    private void removeId(ArrayList<Integer> ids, int id) {
        //Only remove id if its in the list
        if (ids.indexOf(id) != -1) {
           return;
        }

        ids.add(findIndexForId(armyIds, id), id);
    }

    private int findIndexForId(ArrayList<Integer> ids, int id) {
        Collections.sort(ids);
        return Collections.binarySearch(ids, id);
    }

    public String getArmyId() {
        return getId(armyIds);
    }

    public String getColonistId() {
        return getId(colonistIds);
    }

    public String getExplorerId() {
        return getId(explorerIds);
     }

    public String getRangedId() {
        return getId(rangedIds);
     }

    public String getMeleeId() {
        return getId(meleeIds);
     }

    public String getWorkerId() {
        return getId(workerIds);
    }

    public String getCapitalId() {
        return getId(capitalIds);
    }

    public String getFarmId() {
        return getId(farmIds);
    }

    public String getFortId() {
        return getId(fortIds);
    }

    public String getMineId() {
        return getId(mineIds);
    }

    public String getObservationTowerId() {
        return getId(observationTowerIds);
    }

    public String getPowerPlantId() {
        return getId(powerPlantIds);
    }

    public String getUniversityId() {
        return getId(universityIds);
    }

    public void removeArmyId(String idToRemove) {
        removeId(armyIds, Integer.parseInt(idToRemove));
    }


    public void removeCapitalId(String idToRemove) {
        removeId(capitalIds, Integer.parseInt(idToRemove));
    }

    public void removeFarmId(String idToRemove) {
        removeId(farmIds, Integer.parseInt(idToRemove));
    }

    public void removeFortId(String idToRemove) {
        removeId(fortIds, Integer.parseInt(idToRemove));
    }

    public void removeMineId(String idToRemove) {
        removeId(mineIds, Integer.parseInt(idToRemove));
    }

    public void removeObservationTowerId(String idToRemove) {
        removeId(observationTowerIds, Integer.parseInt(idToRemove));
    }

    public void removePowerPlantId(String idToRemove) {
        removeId(powerPlantIds, Integer.parseInt(idToRemove));
    }

    public void removeUniversityId(String idToRemove) {
        removeId(universityIds, Integer.parseInt(idToRemove));
    }

    public void removeRangedId(String idToRemove) {
        removeId(rangedIds, Integer.parseInt(idToRemove));
    }

    public void removeColonistId(String idToRemove) {
        removeId(colonistIds, Integer.parseInt(idToRemove));
    }

    public void removeExplorerId(String idToRemove) {
        removeId(explorerIds, Integer.parseInt(idToRemove));
    }

    public void removeMeleeId(String idToRemove) {
        removeId(meleeIds, Integer.parseInt(idToRemove));
    }
}
