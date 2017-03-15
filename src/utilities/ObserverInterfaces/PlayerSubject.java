package utilities.ObserverInterfaces;

import controller.Observers.PlayerObservator;

/**
 * Created by Konrad on 3/15/2017.
 */
public interface PlayerSubject {
    void registerPlayerObserver(PlayerObservator o);
    void unregister(MiniMapObserver o);
    void notifyObservers();
}
