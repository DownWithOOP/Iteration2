package utilities.ObserverInterfaces;

import model.RenderInformation.StructureRenderInformation;
import model.RenderInformation.StructureRenderObject;
import model.entities.EntityId;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/5/2017.
 */
public interface StructureObserver {
    void update(StructureRenderInformation structureRenderInformation);
    void updateStructure(EntityId structureId, StructureRenderObject structureRenderObject);
    StructureRenderInformation share();
}
