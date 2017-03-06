package controller.Observers;

import controller.AbstractObserver;
import model.map.Map;
import model.map.MapRenderObject;
import model.map.tile.MapRenderInformation;

/**
 * Created by Konrad on 3/3/2017.
 */
public class MainViewObserver  extends AbstractObserver {

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
