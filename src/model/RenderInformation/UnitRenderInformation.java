package model.RenderInformation;

import model.entities.EntityId;
import utilities.id.CustomID;

import java.util.ArrayList;

/**
 * Created by Konrad on 3/5/2017.
 */
public class UnitRenderInformation {

    private ArrayList<UnitRenderObject> unitRenderInformation;
    private ArrayList<ArmyRenderObject> armyRenderInformation;

    public UnitRenderInformation(){
        this.unitRenderInformation = new ArrayList<UnitRenderObject>();
        this.armyRenderInformation = new ArrayList<>();
    }
    public void addUnit(UnitRenderObject unitRenderObject){
        this.unitRenderInformation.add(unitRenderObject);
    }
    public boolean removeUnit(EntityId unitId) {
        for (UnitRenderObject unit : unitRenderInformation) {
            if (unit.getUnitId() == unitId) {
                return unitRenderInformation.remove(unit);
            }
        }
        return false;
    }
    public void addArmy(ArmyRenderObject armyRenderObject) {
        this.armyRenderInformation.add(armyRenderObject);
    }
    public ArrayList<UnitRenderObject> returnRenderInformation(){
        return this.unitRenderInformation;
    }
    public ArrayList<ArmyRenderObject> returnArmyInformation() {return this.armyRenderInformation;}
}
