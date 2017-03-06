package controller.Observers;

import model.RenderInformation.MapRenderInformation;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MapObserver implements utilities.ObserverInterfaces.MapObserver{

    private MapRenderInformation mapRenderInformation;

    @Override
    public void update(MapRenderInformation mapRenderInformation) {
        this.mapRenderInformation = mapRenderInformation;
    }

    @Override
    public MapRenderInformation share() {
        return this.mapRenderInformation;
    }
}
