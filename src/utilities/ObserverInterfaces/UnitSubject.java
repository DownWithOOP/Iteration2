package utilities.ObserverInterfaces;

/**
 * Created by Konrad on 3/5/2017.
 */
public interface UnitSubject {
    void registerUnitObserver(UnitObserver o);
    void unregister(UnitObserver o);
    void notifyUnitObservers();
}
