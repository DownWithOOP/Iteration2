package model.RenderInformation;

import utilities.id.CustomID;

import java.util.ArrayList;

/**
 * Created by Konrad on 3/5/2017.
 */
public class StructureRenderInformation {

    private ArrayList<StructureRenderObject> structureRenderInformation;

    public StructureRenderInformation(){
        this.structureRenderInformation = new ArrayList<StructureRenderObject>();
    }
    public void addStructure(StructureRenderObject structureRenderObject){
        this.structureRenderInformation.add(structureRenderObject);
    }
    public void removeStructure(CustomID structureId){
        for(StructureRenderObject structure : structureRenderInformation){
            if(structure.getStructureId() == structureId){
                structureRenderInformation.remove(structure);
            }
        }
    }
    public ArrayList<StructureRenderObject> returnRenderInformation(){
        return this.structureRenderInformation;
    }
}
