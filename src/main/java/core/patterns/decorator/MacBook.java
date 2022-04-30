package core.patterns.decorator;

public class MacBook {
    private Computer computer;

    public MacBook(Computer computer) {
        this.computer = computer;
    }

    public void setText(String text) {
        computer.typingText(text);
    }

    public void showText() {
        computer.showText();
    }

    public void entertainments() {
        computer.entertainments();
        System.out.println("Помимо этого можно играть и редактировать фотографии! / Макбук");
    }
}
