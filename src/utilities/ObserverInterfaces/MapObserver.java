package utilities.ObserverInterfaces;


import model.RenderInformation.*;
import model.entities.EntityId;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/3/2017.
 */
public interface MapObserver {
    void update(int playerNumber, MapRenderInformation mapRenderInformation, UnitRenderInformation unitRenderInformation, StructureRenderInformation structureRenderInformation);
    void updateUnit(EntityId unitId, UnitRenderObject unitRenderObject);
    void updateStructure(EntityId structureId, StructureRenderObject structureRenderObject);
    MapRenderInformation share();
    TileRenderObject[][] getPlayerXRenderMap(int playerNumber);
}
