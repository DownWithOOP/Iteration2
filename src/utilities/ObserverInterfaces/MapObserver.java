package utilities.ObserverInterfaces;


import model.RenderInformation.MapRenderInformation;

/**
 * Created by Konrad on 3/3/2017.
 */
public interface MapObserver {
    void update(MapRenderInformation mapRenderInformation );
    MapRenderInformation share();
}