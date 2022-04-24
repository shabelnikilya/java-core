package core.patterns.observer;

import java.util.Scanner;

/**
 * Паттерн 'Наблюдатель'.
 *
 * Определяет отношение 'один ко многим' между объектами таким образом,
 * что при изменении состояния одного объекта происходит автоматическое оповещение
 * и обновление всех зависимых объектов.
 *
 */
public class Main {

    /**
     * При обновлении объекта subject, происходит оповещение слушателей
     * и вывод на экран обновленного значения.
     * @param args
     */
    public static void main(String[] args) {

        Subject subject = new Console();
        Observer observer = new FirstObserverImpl(subject);
        Observer secondObserver = new SecondObserverImpl();
        subject.addObserver(secondObserver);

        Scanner sc = new Scanner(System.in);

        System.out.println("Здравствуйте! Введите в консоль что-либо...");
        String answer = sc.nextLine();

        while (!answer.equals("exit")) {

            subject.setText(answer);
            answer = sc.nextLine();
        }
    }
}
