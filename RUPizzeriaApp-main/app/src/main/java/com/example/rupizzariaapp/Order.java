package com.example.rupizzariaapp;

import java.util.ArrayList;

/**
 * Class for creating a pizza order
 * @author Kayla Kam and Nicole Hsieh
 */
public class Order implements Customizable {

    private int orderNumber;
    private int pizzaNumber;
    private ArrayList<Pizza> pizzaorders;
    public static final double TAXRATE = 0.06625;
    private static int count = 0;

    /**
     * Constructor for Order object.
     */
    public Order(){
        this.orderNumber = ++count;
        this.pizzaorders = new ArrayList<Pizza>();
    }

    /**
     * toString method
     * @return String
     */
    public String toString(){
        String returning = "";
        for (Pizza pizza : pizzaorders){
            returning += pizza + " \n";
        }
        return returning;
    }

    /**
     * Get string of order number
     * @return this.orderNumber
     */
    public int getOrderNumber(){
        return this.orderNumber;
    }

    /**
     * get array list of pizza orders
     * @return the arraylist holding the pizzas.
     */
    public ArrayList<Pizza> getPizzaOrders(){
        return this.pizzaorders;
    }

    /**
     * method to find a specific pizza
     * @param target
     * @return Pizza
     */
    public Pizza findPizza(Pizza target){
        for (Pizza pizza : pizzaorders){
            if (pizza.equals(target)){
                return pizza;
            }
        }
        return null;
    }

    /**
     * get the subtotal of the order
     * @return the subtotal price of the pizza in the given order.
     *
     */
    public double getSubtotal(){
        double price = 0;
        for(Pizza pp : pizzaorders){
            price += pp.price();
        }
        return price;
    }

    /**
     * get the tax rate of the subtotal
     * @return the tax rate of the subtotal price of the pizzas.
     */
    public double getTaxrate(){
        return (getSubtotal()*TAXRATE);
    }

    /**
     * get the total of the pizza order
     * @returns the price of the subtotal of the order and the tax.
     */
    public double getTotal(){
        if (pizzaorders.size() == 0){
            return 0;
        }
        return (this.getSubtotal() + getTaxrate());
    }

    /**
    * Adds a pizza into the array list of pizza orders.
    *
    * @param obj a selected type of pizza.
    * @return true if added, false if not added
    */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza){
            if (pizzaorders.add((Pizza)obj)){
                return true;
            }
        }
        return false;
    }

    /**
     * Drops a pizza into the array list of pizza orders.
     * @param obj a selected type of pizza.
     * @return true if removed, false if not removed
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza){
            if (pizzaorders.remove((Pizza) obj)){
                return true;
            }
        }
        return false;
    }

}
