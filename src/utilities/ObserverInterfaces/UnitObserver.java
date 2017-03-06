package utilities.ObserverInterfaces;

import model.RenderInformation.MapRenderInformation;
import model.RenderInformation.UnitRenderInformation;

/**
 * Created by Konrad on 3/5/2017.
 */
public interface UnitObserver {
    void update(UnitRenderInformation unitRenderInformation );
    UnitRenderInformation share();
}
