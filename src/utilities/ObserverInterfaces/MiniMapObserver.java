package utilities.ObserverInterfaces;

import model.RenderInformation.TileRenderObject;

/**
 * Created by Konrad on 3/9/2017.
 */
public interface MiniMapObserver {
    void update(int rightX, int leftX, int rightY, int leftY, TileRenderObject[][] renderData);
}
