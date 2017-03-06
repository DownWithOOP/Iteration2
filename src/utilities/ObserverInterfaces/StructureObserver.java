package utilities.ObserverInterfaces;

import model.RenderInformation.StructureRenderInformation;

/**
 * Created by Konrad on 3/5/2017.
 */
public interface StructureObserver {
    void update(StructureRenderInformation structureRenderInformation);
    StructureRenderInformation share();
}
