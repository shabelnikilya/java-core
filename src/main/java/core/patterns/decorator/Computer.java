package core.patterns.decorator;

/**
 * Модель данных компьютера.
 * Базовые вохможности печатать текст и его просматривать.
 * Виды рахвлечений на компьютере 0 абстрактный метод
 * тк зависит от конкретного компьютера и его возможностей.
 */
public abstract class Computer {
    private String name;
    private String model;
    protected String text;

    public void typingText(String text) {
        this.text = text;
    }

    public void showText() {
        System.out.println(this.text);
    }

    abstract void entertainments();
}
