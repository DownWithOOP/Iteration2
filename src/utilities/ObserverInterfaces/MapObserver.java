package utilities.ObserverInterfaces;


import model.RenderInformation.*;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/3/2017.
 */
public interface MapObserver {
    void update(int playerNumber, MapRenderInformation mapRenderInformation, UnitRenderInformation unitRenderInformation, StructureRenderInformation structureRenderInformation);
    void updateUnit(CustomID unitId, UnitRenderObject unitRenderObject);
    void updateStructure(CustomID structureId, StructureRenderObject structureRenderObject);
    MapRenderInformation share();
    TileRenderObject[][] getPlayerXRenderMap(int playerNumber);
}
