package com.example.rupizzariaapp;

/**
 * class for Deluxe pizza object
 * @author Kayla Kam and Nicole Hsieh
 */
public class Deluxe extends Pizza{

    /**
     * Deluxe pizza constructor
     */
    Deluxe(){
        addTopping(Topping.sausage);
        addTopping(Topping.pepperoni);
        addTopping(Topping.greenPepper);
        addTopping(Topping.onion);
        addTopping(Topping.mushroom);
    }

    /**
     * to String method
     * @return String
     */
    @Override
    public String toString(){
        return "Deluxe " + super.toString();
    }

    /**
     * method to add
     *
     * @param obj object
     * @return true if added, false if not added
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * method to remove
     *
     * @param obj object
     * @return true if removed, false if not removed
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }

    /**
     * method to find the price according to the size of the pizza
     * @return double price of pizza
     */
    @Override
    public double price() {

        if ( super.getSize() == Size.small){
            return 14.99;
        } else if ( super.getSize() == Size.medium){
            return 16.99;
        } else if (super.getSize() == Size.large){
            return 18.99;
        }

        return 0;
    }
}
