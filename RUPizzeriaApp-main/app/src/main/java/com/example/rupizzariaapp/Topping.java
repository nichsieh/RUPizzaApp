package com.example.rupizzariaapp;

import java.util.ArrayList;

/**
 * enum class for toppings
 */
public enum Topping {
    sausage,
    pepperoni,
    greenPepper,
    onion,
    mushroom,
    bbqChicken,
    provolone,
    cheddar,
    beef,
    ham,
    cheese,
    broccoli,
    olive;

    /**
     * get toppings for each pizza type
     * @param type
     * @return
     */
    public static ArrayList<Topping> getToppings(String type){
        ArrayList<Topping> toppings = new ArrayList<>();
        if (type.equalsIgnoreCase("Deluxe")){
            toppings.add(sausage);
            toppings.add(pepperoni);
            toppings.add(greenPepper);
            toppings.add(onion);
            toppings.add(mushroom);
        } else if (type.equalsIgnoreCase("BBQ Chicken")){
            toppings.add(bbqChicken);
            toppings.add(greenPepper);
            toppings.add(provolone);
            toppings.add(cheddar);
        } else if (type.equalsIgnoreCase("Meatzza")){
            toppings.add(sausage);
            toppings.add(pepperoni);
            toppings.add(beef);
            toppings.add(ham);
        }
        return toppings;
    }

}