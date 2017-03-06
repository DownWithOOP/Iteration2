package controller.Observers;

import model.RenderInformation.UnitRenderInformation;

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
    public UnitRenderInformation share() {
        return this.unitRenderInformation;
    }
}
