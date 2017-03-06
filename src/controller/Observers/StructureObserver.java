package controller.Observers;

import model.RenderInformation.StructureRenderInformation;

/**
 * Created by Konrad on 3/5/2017.
 */
public class StructureObserver implements utilities.ObserverInterfaces.StructureObserver {

    private StructureRenderInformation structureRenderInformation;

    @Override
    public void update(StructureRenderInformation structureRenderInformation) {
        this.structureRenderInformation = structureRenderInformation;
    }

    @Override
    public StructureRenderInformation share() {
        return this.structureRenderInformation;
    }
}
