package Utils.Observer;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    List<Observer> observers = new ArrayList<>();

    /**
     * method that adds an observer to the lsit
     * @param e: the Observer
     */
    void addObserver(Observer e);

    /**
     * method that deletes an Observer from the list
     * @param e: the Observer
     */
    void removeObserver(Observer e);

    /**
     * method that notifies all the Observers
     */
    void notifyAllObservers();
}
