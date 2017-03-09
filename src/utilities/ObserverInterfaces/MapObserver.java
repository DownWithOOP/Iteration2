package utilities.ObserverInterfaces;


import model.RenderInformation.MapRenderInformation;
import model.RenderInformation.StructureRenderInformation;
import model.RenderInformation.UnitRenderInformation;

/**
 * Created by Konrad on 3/3/2017.
 */
public interface MapObserver {
    void update(int playerNumber, MapRenderInformation mapRenderInformation, UnitRenderInformation unitRenderInformation, StructureRenderInformation structureRenderInformation);
    MapRenderInformation share();
    int[][] getPlayerXFogOfWarMap(int playerNumber);
}
