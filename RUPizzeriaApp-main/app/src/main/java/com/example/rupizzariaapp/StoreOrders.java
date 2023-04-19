package com.example.rupizzariaapp;
import java.util.ArrayList;

/**
 * class for all the store orders
 * @author Kayla Kam and Nicole Hsieh
 */
public class StoreOrders implements Customizable{
    private ArrayList<Order> orders;
    private ArrayList<Integer> orderNumbers;

    /**
     * Constructor for the Store Orders class.
     */
    public StoreOrders(){
        this.orders = new ArrayList<Order>();
        orderNumbers = new ArrayList<Integer>();
    }

    /**
     * Adds a specific order list into the list of store orders.
     * @param obj which is a Order from the Order class.
     * @return true if added, false if not added
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order){
            orders.add((Order) obj);
            return true;
        }
        return false;
    }

    /**
     *  Drops a specific order list from the list of store orders.
     * @param obj an order from the Order class.
     * @return true if removed, false if not removed
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof  Order){
            orders.remove((Order) obj);
            return true;
        }
        return false;
    }

    /**
     * Returns a list of store orders.
     * @return orders an array list of orders.
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }

    /**
     * get the order numbers of the pizzas
     * @return ArrayList<> array list of integers
     */
    public ArrayList<Integer> getOrderNumbers(){
        for (int i = 0 ; i < orders.size(); i++){
            int number = orders.get(i).getOrderNumber();
            orderNumbers.add(number);
        }
        return orderNumbers;
    }

    /**
     * method to find a specific order using the orderNum
     * @param orderNum String
     * @return Order
     */
    public Order find(String orderNum){
        for(int i = 0; i < orders.size(); i++){
            if(String.valueOf(orders.get(i).getOrderNumber()).equals((orderNum))){
                return orders.get(i);
            }
        }
        return null;
    }

}