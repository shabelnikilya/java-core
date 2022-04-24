package core.patterns.strategy;

/**
 * Изменяемая часть объекта поле {@link #car} является
 * инкапсулируемой частью, которая за счет паттерна Стратегия будет гибко меняться.
 */
public class People {
    private String name;
    private String lastName;
    private String telephoneNumber;
    private Car car;

    public People(String name, String lastName, String telephoneNumber, Car car) {
        this.name = name;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.car = car;
    }

    public void showCar() {
        System.out.println(this.car.modelCar());
    }
}
