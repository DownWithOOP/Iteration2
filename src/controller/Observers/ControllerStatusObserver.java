package controller.Observers;

import model.RenderInformation.StatusRenderInformation;
import utilities.ObserverInterfaces.StatusObserver;

/**
 * Created by Jonathen on 3/7/2017.
 */
public class ControllerStatusObserver implements StatusObserver {

    private StatusRenderInformation statusRenderInformation;

    @Override
    public void update(StatusRenderInformation statusRenderInformation) {
        this.statusRenderInformation = statusRenderInformation;
    }

    @Override
    public StatusRenderInformation share() {
        return this.statusRenderInformation;
    }
}
