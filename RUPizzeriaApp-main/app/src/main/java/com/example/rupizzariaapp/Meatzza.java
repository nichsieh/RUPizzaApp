package com.example.rupizzariaapp;

/**
 * class for Meatzza pizza object
 * @author Kayla Kam and Nicole Hsieh
 */
public class Meatzza extends Pizza{
    /**
     * Meatzza object constructor
     */
    Meatzza(){
        addTopping(Topping.sausage);
        addTopping(Topping.pepperoni);
        addTopping(Topping.beef);
        addTopping(Topping.ham);
    }

    /**
     * toString method
     * @return String
     */
    @Override
    public String toString(){
        return "Meatzza " + super.toString();
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
            return 15.99;
        } else if ( super.getSize() == Size.medium){
            return 17.99;
        } else if (super.getSize() == Size.large){
            return 19.99;
        }
        return 0;
    }
}
