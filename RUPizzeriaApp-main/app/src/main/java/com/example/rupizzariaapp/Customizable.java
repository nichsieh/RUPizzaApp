package com.example.rupizzariaapp;

/**
 * Customizable Interface for adding or removing orders or toppings
 *  @author Kayla Kam and Nicole Hsieh
 * @param <E>
 */
public interface Customizable<E> {

    /**
     * method to add
     * @param obj object
     * @return true if added, false if not added
     */
    boolean add(Object obj);

    /**
     * method to remove
     * @param obj object
     * @return true if removed, false if not removed
     */
    boolean remove(Object obj);
}
