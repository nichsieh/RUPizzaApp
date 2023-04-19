package com.example.rupizzariaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NYPizzaActivity extends AppCompatActivity {
    private ImageView NYPizzaImageChanging;
    private TextView CrustAutoCompleteTextView,totalAutoCompleteTextView;
    private Spinner pizza_type_spinner;
    private RadioGroup radioGroup;
    private RadioButton smallSize, mediumSize, largeSize;
    private CheckBox sausage, pepperoni, greenPepper, onion, mushroom, bbqChicken, provolone,
            cheddar, beef, ham, cheese, broccoli, olive;

    private final NYPizza nyPizza = new NYPizza();
    private Pizza pizza;
    private String pizzaType;
    private Size pizzaSize;
    private static final int MAXTOPPINGS = 7;
    static ArrayList<Topping> toppings = new ArrayList<>();
    private double pizzaPrice, toppingPrice;
    private Crust pizzaCrust;
    private List<String> pizzaTypes;

    /**
     * method to be called at the start of the screen
     * @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_pizza);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(NYPizzaActivity.this,
                        MainActivity.class);
                startActivity(button);
            }
        });

        initWidgets();
        defaultValues();

        // pizza type spinner
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types_of_pizzas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizza_type_spinner.setAdapter(adapter);
        pizza_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resetToppings();
                // get pizza type
                pizzaType = parent.getItemAtPosition(position).toString();
                // toast to let user know type was selected
                Toast.makeText(NYPizzaActivity.this, "Type selected: " + pizzaType, Toast.LENGTH_SHORT).show();
                // get crust type according to pizza type
                pizzaCrust = Crust.getCrust(pizzaType, "NY");
                // setting the images
                if (pizzaType.equalsIgnoreCase("Deluxe")) {
                    NYPizzaImageChanging.setImageResource(R.drawable.ny_deluxe);
                } else if (pizzaType.equalsIgnoreCase("Meatzza")) {
                    NYPizzaImageChanging.setImageResource(R.drawable.ny_meatzza);
                } else if (pizzaType.equalsIgnoreCase("BBQ Chicken")) {
                    NYPizzaImageChanging.setImageResource(R.drawable.ny_bbq_chicken);
                } else {
                    NYPizzaImageChanging.setImageResource(R.drawable.ny_pizza);
                }
                // setting crust text view
//                CrustAutoCompleteTextView.setText(Crust.crustInfo(pizzaCrust));
                setPrice();

                if (!pizzaType.equalsIgnoreCase("Build Your Own")){
                    setToppings();
                } else{
                    getToppingsForBYO();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // select size
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton == smallSize){
                    pizzaSize = Size.small;
                } else if (radioButton == mediumSize){
                    pizzaSize = Size.medium;
                } else if (radioButton == largeSize){
                    pizzaSize = Size.large;
                } else {
                    pizzaSize = null;
                }

                setPrice();

//                 on below line we are displaying a toast message.
                Toast.makeText(NYPizzaActivity.this, "Size selected: " + pizzaSize.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        toppingsListener();

        AlertDialog.Builder alert = new AlertDialog.Builder(this); //alerts
        alert.setCancelable(true);

        Button addPizzaButton = findViewById(R.id.AddOrderButton);
        addPizzaButton.setOnClickListener(view -> {
            String error = errorMessages();
            if (error.isEmpty()){
//                 type is selected automatically when the user uses the dropdown box
                if (pizzaType.equalsIgnoreCase("Build Your Own")){
                    pizza = nyPizza.createBuildYourOwn();
                } else if (pizzaType.equalsIgnoreCase("Deluxe")) {
                    pizza = nyPizza.createDeluxe();
                } else if (pizzaType.equalsIgnoreCase("BBQ Chicken")) {
                    pizza = nyPizza.createBBQChicken();
                } else if (pizzaType.equalsIgnoreCase("Meatzza")) {
                    pizza = nyPizza.createMeatzza();
                }

                pizza.setType("NY");
                pizza.setSize(pizzaSize);
                pizza.setCrust(pizzaCrust);

                if (pizzaType.equalsIgnoreCase("Build Your Own")){
                    for (Topping topping : toppings)
                        pizza.addTopping(topping);
                }

                if (MainActivity.currentOrder.add(pizza)){
                    alert.setMessage("Successfully added to order!").setPositiveButton("OK",
                            new     DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.e("info", "OK");
                                }
                            });
                    alert.show();
                } else {
                    alert.setTitle(Crust.crustInfo(pizzaCrust));
                    alert.setMessage("Your order was not added successfully.");
                    alert.show();
                }

            } else {
                Toast.makeText(NYPizzaActivity.this, error, Toast.LENGTH_LONG).show();
            }

        });
    }

    /**
     * initiate all widgets
     */
    private void initWidgets(){
        //initialize .xml variables
        pizza_type_spinner = findViewById(R.id.pizza_type_spinner);
        // radio button
        smallSize = findViewById(R.id.smallSize);
        mediumSize = findViewById(R.id.mediumSize);
        largeSize = findViewById(R.id.largeSize);
        // image view
        NYPizzaImageChanging = findViewById(R.id.NYPizzaImageChanging);
        // toppings
        totalAutoCompleteTextView = findViewById(R.id.totalAutoCompleteTextView);
        CrustAutoCompleteTextView = findViewById(R.id.CrustAutoCompleteTextView);
        //checkboxes
        sausage = findViewById(R.id.sausage);
        pepperoni = findViewById(R.id.pepperoni);
        greenPepper = findViewById(R.id.greenPepper);
        onion = findViewById(R.id.onion);
        mushroom = findViewById(R.id.mushroom);
        bbqChicken = findViewById(R.id.bbqChicken);
        provolone = findViewById(R.id.provolone);
        cheddar = findViewById(R.id.cheddar);
        beef = findViewById(R.id.beef);
        ham = findViewById(R.id.ham);
        cheese = findViewById(R.id.cheese);
        broccoli = findViewById(R.id.broccoli);
        olive = findViewById(R.id.olive);
    }

    /**
     * set all default values
     */
    private void defaultValues(){
        pizzaSize = null;
        pizzaType = null;
        pizzaPrice = 0;
        toppingPrice = 0;
        totalAutoCompleteTextView.setText(String.valueOf(pizzaPrice));
        pizzaTypes = new ArrayList<>(Arrays.asList("Build Your Own", "BBQ Chicken", "Deluxe", "Meatzza"));
    }

    /**
     * Listener for the toppings
     */
    private void toppingsListener(){
        sausage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        pepperoni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        greenPepper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        onion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        mushroom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        bbqChicken.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        provolone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        cheddar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        beef.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        ham.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        cheese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        broccoli.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
        olive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getToppingsForBYO();
            }
        });
    }

    /**
     * error message when adding a pizza to a current order
     * @return String - the error message
     */
    private String errorMessages(){
        if (pizzaType.equalsIgnoreCase(""))
            return "Choose your pizza type";
        if (getSize() == null) // getSize returns "" if not a small, medium or large
            return "Choose your pizza size";
        if (toppings.size() <= 0)
            return "Choose your toppings";
        return "";
    }

    /**
     * set price for the pizza
     */
    public void setPrice(){
        if (pizzaType != null && pizzaSize != null){
            if (pizzaType.equalsIgnoreCase("Deluxe")){
                if (pizzaSize.equals(Size.small)){
                    pizzaPrice = 14.99;
                } else if (pizzaSize.equals(Size.medium)){
                    pizzaPrice = 16.99;
                } else if (pizzaSize.equals(Size.large)){
                    pizzaPrice = 18.99;
                }
            } else if (pizzaType.equalsIgnoreCase("BBQ Chicken")){
                if (pizzaSize.equals(Size.small)){
                    pizzaPrice = 13.99;
                } else if (pizzaSize.equals(Size.medium)){
                    pizzaPrice = 15.99;
                } else if (pizzaSize.equals(Size.large)){
                    pizzaPrice = 17.99;
                }
            } else if (pizzaType.equalsIgnoreCase("Meatzza")){
                if (pizzaSize.equals(Size.small)){
                    pizzaPrice = 15.99;
                } else if (pizzaSize.equals(Size.medium)){
                    pizzaPrice = 17.99;
                } else if (pizzaSize.equals(Size.large)){
                    pizzaPrice = 19.99;
                }
            } else if (pizzaType.equalsIgnoreCase("Build Your Own")){
                if (pizzaSize.equals(Size.small)){
                    pizzaPrice = 8.99;
                } else if (pizzaSize.equals(Size.medium)){
                    pizzaPrice = 10.99;
                } else if (pizzaSize.equals(Size.large)){
                    pizzaPrice = 12.99;
                }
            } else {
                pizzaPrice = 0;
            }

            if (!pizzaType.equalsIgnoreCase("Build Your Own")){
                totalAutoCompleteTextView.setText(String.valueOf(pizzaPrice));
            } else {
                totalAutoCompleteTextView.setText(String.valueOf(pizzaPrice + toppingPrice));
            }
        }
    }

    /**
     * get the size of the pizza from the radio button
     * @return Size
     */
    private Size getSize(){
        return pizzaSize;
    }

    /**
     * reset the checkboxes and the toppings list.
     */
    private void resetToppings(){
        if (toppings.size() != 0){
            toppings.removeAll(toppings.subList(0, toppings.size()));
        }
        sausage.setEnabled(true);
        sausage.setChecked(false);
        pepperoni.setEnabled(true);
        pepperoni.setChecked(false);
        greenPepper.setEnabled(true);
        greenPepper.setChecked(false);
        onion.setEnabled(true);
        onion.setChecked(false);
        mushroom.setEnabled(true);
        mushroom.setChecked(false);
        bbqChicken.setEnabled(true);
        bbqChicken.setChecked(false);
        provolone.setEnabled(true);
        provolone.setChecked(false);
        cheddar.setEnabled(true);
        cheddar.setChecked(false);
        beef.setEnabled(true);
        beef.setChecked(false);
        ham.setEnabled(true);
        ham.setChecked(false);
        cheese.setEnabled(true);
        cheese.setChecked(false);
        broccoli.setEnabled(true);
        broccoli.setChecked(false);
        olive.setEnabled(true);
        olive.setChecked(false);
    }

    /**
     * get the toppings for the deluxe, meatzza or bbqchicken pizza.
     */
    private void setToppings(){
        if (pizzaType.equalsIgnoreCase("Deluxe")) {
            toppings = Topping.getToppings("Deluxe");
        } else if (pizzaType.equalsIgnoreCase("BBQ Chicken")) {
            toppings = Topping.getToppings("BBQ Chicken");
        } else if (pizzaType.equalsIgnoreCase("Meatzza")) {
            toppings = Topping.getToppings("Meatzza");
        }

        setToppingBoxes(toppings);
    }

    /**
     * select the boxes for which toppings are in the deluxe, meatzza or bbqchicken pizza.
     * @param toppings arraylist of topping
     */
    private void setToppingBoxes(ArrayList<Topping> toppings){
        for (Topping topping : toppings){
            if (topping.equals(Topping.sausage))
                sausage.setChecked(true);
            else if (topping.equals(Topping.pepperoni))
                pepperoni.setChecked(true);
            else if (topping.equals(Topping.greenPepper))
                greenPepper.setChecked(true);
            else if (topping.equals(Topping.onion))
                onion.setChecked(true);
            else if (topping.equals(Topping.mushroom))
                mushroom.setChecked(true);
            else if (topping.equals(Topping.bbqChicken))
                bbqChicken.setChecked(true);
            else if (topping.equals(Topping.provolone))
                provolone.setChecked(true);
            else if (topping.equals(Topping.cheddar))
                cheddar.setChecked(true);
            else if (topping.equals(Topping.beef))
                beef.setChecked(true);
            else if (topping.equals(Topping.ham))
                ham.setChecked(true);
            else if (topping.equals(Topping.cheese))
                cheese.setChecked(true);
            else if (topping.equals(Topping.broccoli))
                broccoli.setChecked(true);
            else if (topping.equals(Topping.olive))
                olive.setChecked(true);
        }

        makeToppingsUnavailable();
    }

    /**
     * disable all checkboxes that are not selected
     */
    private void makeToppingsUnavailable(){
        if (!sausage.isChecked())
            sausage.setEnabled(false);
        if (!pepperoni.isChecked())
            pepperoni.setEnabled(false);
        if (!greenPepper.isChecked())
            greenPepper.setEnabled(false);
        if (!onion.isChecked())
            onion.setEnabled(false);
        if (!mushroom.isChecked())
            mushroom.setEnabled(false);
        if (!bbqChicken.isChecked())
            bbqChicken.setEnabled(false);
        if (!provolone.isChecked())
            provolone.setEnabled(false);
        if (!cheddar.isChecked())
            cheddar.setEnabled(false);
        if (!beef.isChecked())
            beef.setEnabled(false);
        if (!ham.isChecked())
            ham.setEnabled(false);
        if (!cheese.isChecked())
            cheese.setEnabled(false);
        if (!broccoli.isChecked())
            broccoli.setEnabled(false);
        if (!olive.isChecked())
            olive.setEnabled(false);
    }

    /**
     * make all the topping checkboxes available
     */
    private void makeToppingAvailable(){
        sausage.setEnabled(true);
        pepperoni.setEnabled(true);
        greenPepper.setEnabled(true);
        onion.setEnabled(true);
        mushroom.setEnabled(true);
        bbqChicken.setEnabled(true);
        provolone.setEnabled(true);
        cheddar.setEnabled(true);
        beef.setEnabled(true);
        ham.setEnabled(true);
        cheese.setEnabled(true);
        broccoli.setEnabled(true);
        olive.setEnabled(true);
    }

    /**
     * get the toppings for the build your own pizza
     */
    public void getToppingsForBYO(){
        if (pizzaType != null ){
            if (toppings.size() != 0){
                toppings.removeAll(toppings.subList(0, toppings.size()));
            }
            if (pizzaType.equalsIgnoreCase("Build Your Own")){
                if (sausage.isChecked())
                    toppings.add(Topping.sausage);
                if (pepperoni.isChecked())
                    toppings.add(Topping.pepperoni);
                if (greenPepper.isChecked())
                    toppings.add(Topping.greenPepper);
                if (onion.isChecked())
                    toppings.add(Topping.onion);
                if (mushroom.isChecked())
                    toppings.add(Topping.mushroom);
                if (bbqChicken.isChecked())
                    toppings.add(Topping.bbqChicken);
                if (provolone.isChecked())
                    toppings.add(Topping.provolone);
                if (cheddar.isChecked())
                    toppings.add(Topping.cheddar);
                if (beef.isChecked())
                    toppings.add(Topping.beef);
                if (ham.isChecked())
                    toppings.add(Topping.ham);
                if (cheese.isChecked())
                    toppings.add(Topping.cheese);
                if (broccoli.isChecked())
                    toppings.add(Topping.broccoli);
                if (olive.isChecked())
                    toppings.add(Topping.olive);

                if (toppings.size() > MAXTOPPINGS - 1){
                    makeToppingsUnavailable();
                } else {
                    makeToppingAvailable();
                }
                updatePrice();
            }
        }
    }

    /**
     * updates the price of the pizza. Used for BYO pizza
     */
    private void updatePrice(){
        toppingPrice = toppings.size() * 1.59;
        totalAutoCompleteTextView.setText(String.format("%.2f", pizzaPrice + toppingPrice));
    }

}