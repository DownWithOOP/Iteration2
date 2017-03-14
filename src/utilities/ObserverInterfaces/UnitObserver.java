package utilities.ObserverInterfaces;

import model.RenderInformation.MapRenderInformation;
import model.RenderInformation.UnitRenderInformation;
import model.RenderInformation.UnitRenderObject;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/5/2017.
 */
public interface UnitObserver {
    void update(UnitRenderInformation unitRenderInformation);
    void updateUnit(CustomID unitId, UnitRenderObject unitRenderObject);
    UnitRenderInformation share();
}
