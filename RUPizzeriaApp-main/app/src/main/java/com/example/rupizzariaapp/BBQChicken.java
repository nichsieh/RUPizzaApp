package com.example.rupizzariaapp;

/**
 * class for BBQ Chicken pizza object
 * @author Kayla Kam and Nicole Hsieh
 */
public class BBQChicken extends Pizza{
    /**
     * BBQ Chicken constructor
     */
    BBQChicken(){
        addTopping(Topping.bbqChicken);
        addTopping(Topping.greenPepper);
        addTopping(Topping.provolone);
        addTopping(Topping.cheddar);
    }

    /**
     * to String method for BBQ Chicken
     * @return String of the bbqChicken
     */
    @Override
    public String toString(){
        return "BBQ Chicken " + super.toString();
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
            return 13.99;
        } else if ( super.getSize() == Size.medium){
            return 15.99;
        } else if (super.getSize() == Size.large){
            return 17.99;
        }
        return 0;
    }
}
