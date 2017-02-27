package utilities;

/**
 * Created by cduica on 2/21/17.
 *
 *
 * This is the subject being absorbed in the model
 */
public interface Subject {

    public void setObserver(Observer o);
    public void notifyObserver();

}
