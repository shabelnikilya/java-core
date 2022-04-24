package core.patterns.strategy;

/**
 * Паттерн проектирования 'Стратегия'.
 *
 * Данный паттерн определяет семейство алгоритмов, инкапсулирует каждый из них
 * и обеспечивает взаимозаменяемость. Он позволяет модифицировать алгоритмы независимо от их
 * использования клиента.
 */
public class MainStrategy {

    public static void main(String[] args) {
        People first = new People("Ilya", "Pavl", "+1111", new BmwCar());
        first.showCar();
        System.out.println("_________________");

        People second = new People("Roma", "Kochet", "+2222", new MazdaCar());
        second.showCar();
    }
}
