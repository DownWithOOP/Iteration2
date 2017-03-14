package controller.Observers;

import model.RenderInformation.StructureRenderInformation;
import model.RenderInformation.StructureRenderObject;
import model.entities.EntityId;
import model.entities.structure.Structure;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/5/2017.
 */
public class StructureObserver implements utilities.ObserverInterfaces.StructureObserver {

    private StructureRenderInformation structureRenderInformation;

    @Override
    public void update(StructureRenderInformation structureRenderInformation) {
        this.structureRenderInformation = structureRenderInformation;
    }

    @Override
    public void updateStructure(EntityId structureId, StructureRenderObject structureRenderObject){
        structureRenderInformation.removeStructure(structureId);
        structureRenderInformation.addStructure(structureRenderObject);
    }

    @Override
    public StructureRenderInformation share() {
        return this.structureRenderInformation;
    }
}
