package utilities.ObserverInterfaces;

/**
 * Created by Jonathen on 3/7/2017.
 */
public interface StatusSubject {
    void registerStatusObserver(StatusObserver o);
    void unregister(StatusObserver o);
    void notifyStatusObservers();
}
