package utilities.ObserverInterfaces;

/**
 * Created by Konrad on 3/9/2017.
 */
public interface MiniMapSubject {
    void registerStatusObserver(MiniMapObserver o);
    void unregister(MiniMapObserver o);
    void notifyObservers();
}
