package model.RenderInformation;

import utilities.id.CustomID;

import java.util.ArrayList;

/**
 * Created by Konrad on 3/5/2017.
 */
public class UnitRenderInformation {

    private ArrayList<UnitRenderObject> unitRenderInformation;

    public UnitRenderInformation(){
        this.unitRenderInformation = new ArrayList<UnitRenderObject>();
    }
    public void addUnit(UnitRenderObject unitRenderObject){
        this.unitRenderInformation.add(unitRenderObject);
    }
    public boolean removeUnit(CustomID unitId){
        for(UnitRenderObject unit : unitRenderInformation){
            if(unit.getUnitId() == unitId){
                return unitRenderInformation.remove(unit);
            }
        }
        return false;
    }
    public ArrayList<UnitRenderObject> returnRenderInformation(){
        return this.unitRenderInformation;
    }
}
