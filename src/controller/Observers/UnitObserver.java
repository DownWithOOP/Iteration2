package controller.Observers;

import model.RenderInformation.UnitRenderInformation;
import model.RenderInformation.UnitRenderObject;
import model.entities.EntityId;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/5/2017.
 */
public class UnitObserver implements utilities.ObserverInterfaces.UnitObserver {

    private UnitRenderInformation unitRenderInformation;

    @Override
    public void update(UnitRenderInformation unitRenderInformation) {
        this.unitRenderInformation = unitRenderInformation;
    }

    @Override
    public void updateUnit(EntityId unitId, UnitRenderObject unitRenderObject){
        unitRenderInformation.removeUnit(unitId);
        unitRenderInformation.addUnit(unitRenderObject);
    }

    @Override
    public UnitRenderInformation share() {
        return this.unitRenderInformation;
    }
}
