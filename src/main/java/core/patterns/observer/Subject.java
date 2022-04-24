package core.patterns.observer;

public interface Subject {

    void setText(String text);

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
