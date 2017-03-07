package utilities.ObserverInterfaces;

import model.RenderInformation.StatusRenderInformation;

/**
 * Created by Jonathen on 3/7/2017.
 */
public interface StatusObserver {
    void update(StatusRenderInformation statusRenderInformation );
    StatusRenderInformation share();
}
