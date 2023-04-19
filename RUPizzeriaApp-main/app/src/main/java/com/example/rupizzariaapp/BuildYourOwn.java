package com.example.rupizzariaapp;

/**
 * class for Build Your Own Pizza object
 * @author Kayla Kam and Nicole Hsieh
 */
public class BuildYourOwn extends Pizza{
    /**
     * build your own object constructor
     */
    BuildYourOwn(){

    }

    /**
     * to String method for Build Your Own Pizza
     * @return string of the build your own object
     */
    @Override
    public String toString(){
        return "Build Your Own " + super.toString();
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
        double additional = (this.sizeToppings()*1.59);
        if ( super.getSize() == Size.small){
            return 8.99  + additional;
        } else if ( super.getSize() == Size.medium){
            return 10.99 + additional;
        } else if (super.getSize() == Size.large){
            return 12.99 + additional;
        }
        return 0;
    }

}
