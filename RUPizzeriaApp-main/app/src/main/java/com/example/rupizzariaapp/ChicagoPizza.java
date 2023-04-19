package com.example.rupizzariaapp;

/**
 * class for Chicago pizza object
 * @author Kayla Kam and Nicole Hsieh
 */
public class ChicagoPizza implements PizzaFactory{
    /**
     * pizza object to create the other pizzas
     */
    private Pizza pizza;

    /**
     * creates a Deluxe pizza type
     *
     * @return Pizza
     */
    @Override
    public Pizza createDeluxe() {
        pizza = new Deluxe();
        return pizza;
    }

    /**
     * creates a Meatzza pizza type
     *
     * @return Pizza
     */
    @Override
    public Pizza createMeatzza() {
        pizza = new Meatzza();
        return pizza;
    }

    /**
     * creates a BBQChiekn pizza type
     *
     * @return Pizza
     */
    @Override
    public Pizza createBBQChicken() {
        pizza = new BBQChicken();
        return pizza;
    }

    /**
     * creates a Build Your Own pizza type
     *
     * @return Pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        pizza = new BuildYourOwn();
        return pizza;
    }
}
