package model.RenderInformation;

import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by Konrad on 3/5/2017.
 */
public class UnitRenderObject {

    private int locationX; // X location on map Unit is located
    private int locationY; // Y location on map Unit is located
    private IdType idType; // type of unit so we know which assets to render, along with unit ownership
    private CustomID unitId;
    private UnitStats unitStats;
    // TODO later add more info like stats

    public UnitRenderObject(CustomID unitId, IdType idType, int locationX, int locationY, UnitStats unitStats){
        this.unitId = unitId;
        this.idType = idType;
        this.locationX = locationX;
        this.locationY = locationY;
        this.unitStats = unitStats;
    }
    public IdType getIdType(){
        return this.idType;
    }
    public CustomID getUnitId(){
        return this.unitId;
    }
    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }
    public UnitStats getUnitStats(){return this.unitStats; };

}
