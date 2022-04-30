package core.patterns.decorator;

/**
 * Паттерн - 'Декоратор'.
 * Паттерн Декоратор динамически наделяет объект новыми возможностями
 * и является гибкой альтернативой субклассированию в области расширения функциональности.
 *
 */
public class Main {
    public static void main(String[] args) {
        Computer computer = new LenovoComputer();
        computer.typingText("Условный текст");
        computer.showText();
        computer.entertainments();

        System.out.println("________________________");
        System.out.println("Вывод объекта декоратора!");

        MacBook macBook = new MacBook(computer);
        macBook.setText("Условный текст декоратора");
        macBook.showText();
        macBook.entertainments();
    }
}
