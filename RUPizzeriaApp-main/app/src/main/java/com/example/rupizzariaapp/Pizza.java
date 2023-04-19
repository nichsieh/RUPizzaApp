package com.example.rupizzariaapp;

import java.util.ArrayList;
import java.util.Locale;

/**
 * class for the Pizza abstract object
 *  @author Kayla Kam and Nicole Hsieh
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings = new ArrayList<>();
    private Crust crust;
    private Size size;
    private String type;

    /**
     * abstract method for the price
     * @return
     */
    public abstract double price();

    /**
     * pizza object constructor
     */
    Pizza(){
        size = Size.small;
        crust = Crust.NYBYO;
    }

    /**
     * set pizza type. either New York or Chicago
     * @param pizza string of pizza name
     */
    public void setType(String pizza){
        if (pizza.equalsIgnoreCase("NY")){
            type = "New York Style";
        } else if (pizza.equalsIgnoreCase("Chicago")){
            type = "Chicago Style";
        }
    }

    /**
     * to String method for pizza
     * @return String with pizza information
     */
    public String toString(){
        return  "(" + type + "-" + Crust.crustInfo(crust) + ") " + toStringToppings() + " " + size.toString().toUpperCase(Locale.ROOT) + " " + price();
    }

    /**
     * to String method for the toppings in this pizza
     * @return String
     */
    private String toStringToppings(){
        String returning = "";
        for ( Topping topping : toppings){
            returning += topping.toString() + ", ";
        }
        return returning;
    }

    /**
     * Method to add toppings to the pizza
     * @param topping Topping from topping class
     */
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    /**
     * Method to remove topping from pizza
     * @param topping Topping from topping class
     */
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    /**
     * Method to return the size/number of toppings in the pizza
     * @return int number of toppings in the pizza
     */
    public int sizeToppings(){
        return toppings.size();
    }

    /**
     * Return the size of the pizza
     * @return size - the size of the pizza from size class
     */
    public Size getSize() {
        return size;
    }

    /**
     * Set the size of the pizza.
     * @param size size - the size of the pizza from size class
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * se the crust of the pizza
     * @param crust crust from the crust class
     */
    public void setCrust(Crust crust){
        this.crust = crust;
    }
}
