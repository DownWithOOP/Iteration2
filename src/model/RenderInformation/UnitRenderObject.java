package model.RenderInformation;

import utilities.id.IdType;

/**
 * Created by Konrad on 3/5/2017.
 */
public class UnitRenderObject {

    private int locationX; // X location on map Unit is located
    private int locationY; // Y location on map Unit is located
    private IdType idType; // type of unit so we know which assets to render, along with unit ownership
    // TODO later add more info like stats

    public UnitRenderObject(IdType idType, int locationX, int locationY){
        this.idType = idType;
        this.locationX = locationX;
        this.locationY = locationY;
    }
    public IdType getIdType(){
        return this.idType;
    }
    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }

}
