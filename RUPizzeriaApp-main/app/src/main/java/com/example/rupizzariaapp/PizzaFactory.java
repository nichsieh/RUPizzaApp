package com.example.rupizzariaapp;

/**
 * interface for the Pizza factory that has methods to call each type of pizza
 * @author Kayla Kam and Nicole Hsieh
 */
public interface PizzaFactory {
    /**
     * creates a Deluxe pizza type
     * @return Pizza
     */
    Pizza createDeluxe();

    /**
     * creates a Meatzza pizza type
     * @return Pizza
     */
    Pizza createMeatzza();

    /**
     * creates a BBQChiekn pizza type
     * @return Pizza
     */
    Pizza createBBQChicken();

    /**
     * creates a Build Your Own pizza type
     * @return Pizza
     */
    Pizza createBuildYourOwn();
}
