package core.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Console implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String text;

    public Console() {
    }

    @Override
    public void setText(String text) {
        this.text = text;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(obs -> obs.readAnswer(text));
    }
}
