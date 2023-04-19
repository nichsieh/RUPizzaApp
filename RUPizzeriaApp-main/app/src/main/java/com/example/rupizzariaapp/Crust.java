package com.example.rupizzariaapp;

/**
 * crust enum
 */
public enum Crust {

    /**
     * Chicago Deluxe pizza
     */
    ChicagoDeluxe ("Deep Dish"),

    /**
     * NY Deluxe pizza
     */
    NYDeluxe ("Brooklyn"),

    /**
     * Chicago BBQ Chicken
     */
    ChicagoBBQChicken("Pan"),

    /**
     * NY BBQ Chicken
     */
    NYBBQChicken("Thin"),

    /**
     * Chicago Meatzza
     */
    ChicagoMeatzza ("Stuffed"),

    /**
     * NY Meatzza
     */
    NYMeatzza ("Hand Tossed"),

    /**
     * Chicago Build Your Own
     */
    ChicagoBYO ("Pan"),

    /**
     * NY Build Your Own
     */
    NYBYO ("Hand Tossed");

    /**
     * String of the crust type
     */
    private String crust = "";

    /**
     * crust constructor
     * @param crust crust
     */
    Crust(String crust) {
        this.crust = crust;
    }

    /**
     * crust information
     * @param crust Crust
     * @return String
     */
    public static String crustInfo(Crust crust){
        return crust.crust;
    }

    /**
     * getCrust method to get the crust type
     * @param pizza Pizza as a string
     * @param type type as a string
     * @return crust
     */
    public static Crust getCrust(String pizza, String type){
        if (type.equalsIgnoreCase("New York")){
            if (pizza.equalsIgnoreCase("Deluxe")){
                return NYDeluxe;
            } else if (pizza.equalsIgnoreCase("Meatzza")){
                return NYMeatzza;
            } else if (pizza.equalsIgnoreCase("BBQ Chicken")){
                return NYBBQChicken;
            } else if (pizza.equalsIgnoreCase("Build Your Own")){
                return NYBYO;
            } else {
                return null;
            }
        } else if (type.equalsIgnoreCase("Chicago")){
            if (pizza.equalsIgnoreCase("Deluxe")){
                return ChicagoDeluxe;
            } else if (pizza.equalsIgnoreCase("Meatzza")){
                return ChicagoMeatzza;
            } else if (pizza.equalsIgnoreCase("BBQ Chicken")){
                return ChicagoBBQChicken;
            } else if (pizza.equalsIgnoreCase("Build Your Own")){
                return ChicagoBYO;
            } else {
                return null;
            }
        }
        return null;
    }

}
