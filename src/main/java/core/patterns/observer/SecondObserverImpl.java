package core.patterns.observer;

public class SecondObserverImpl implements Out, Observer {
    private String text;

    @Override
    public void readAnswer(String answer) {
        this.text = answer;
        show();
    }

    @Override
    public void show() {
        System.out.print("Ответ паттерна Наблюдатель: " + text + " / ");
        System.out.println("Класс наблюдателя - " + this.getClass());
    }
}
