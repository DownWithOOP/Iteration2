package utilities.ObserverInterfaces;

import model.RenderInformation.MapRenderInformation;
import model.RenderInformation.UnitRenderInformation;
import model.RenderInformation.UnitRenderObject;
import model.entities.EntityId;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/5/2017.
 */
public interface UnitObserver {
    void update(UnitRenderInformation unitRenderInformation);
    void updateUnit(EntityId unitId, UnitRenderObject unitRenderObject);
    UnitRenderInformation share();
}
