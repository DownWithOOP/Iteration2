package utilities.ObserverInterfaces;

/**
 * Created by Konrad on 3/5/2017.
 */
public interface StructureSubject {
    void registerStructureObserver(StructureObserver o);
    void unregister(StructureObserver o);
    void notifyStructureObservers();
}
