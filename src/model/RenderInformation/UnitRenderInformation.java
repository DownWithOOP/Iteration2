package model.RenderInformation;

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
    public ArrayList<UnitRenderObject> returnRenderInformation(){
        return this.unitRenderInformation;
    }
}
