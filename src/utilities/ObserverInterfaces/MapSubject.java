package utilities.ObserverInterfaces;

/**
 * Created by Konrad on 3/3/2017.
 * Used for Observer Desgin pattern between the player map object and the mainView Controller which will render the map
 */
public interface MapSubject {
    void register(MapObserver o);
    void unregister(MapObserver o);
    void notifyMapObservers();
}
