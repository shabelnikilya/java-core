package core.patterns.observer;

public class FirstObserverImpl implements Observer, Out {
    private Subject subject;
    private String text;

    public FirstObserverImpl(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void show() {
        System.out.print("Ответ паттерна Наблюдатель: " + text + " / ");
        System.out.println("Класс наблюдателя - " + this.getClass());
    }

    @Override
    public void readAnswer(String answer) {
        this.text = answer;
        show();
    }
}
