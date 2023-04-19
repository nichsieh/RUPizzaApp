package com.example.rupizzariaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static Order currentOrder = new Order();
    public static StoreOrders storeOrder = new StoreOrders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chicagoPizzaButton = findViewById(R.id.chicagoPizzaButton);
        chicagoPizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(MainActivity.this,
                        ChicagoPizzaActivity.class);
                startActivity(button);
            }
        });

        Button nyPizzaButton = findViewById(R.id.nyPizzaButton);
        nyPizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(MainActivity.this,
                        NYPizzaActivity.class);
                startActivity(button);
            }
        });

        Button currentOrderButton = findViewById(R.id.currentOrderButton);
        currentOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(MainActivity.this,
                        CurrentOrderActivity.class);
                startActivity(button);
            }
        });

        Button storeOrdersButton = findViewById(R.id.storeOrderButton);
        storeOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(MainActivity.this,
                        StoreOrdersActivity.class);
                startActivity(button);
            }
        });
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button chicagoPizzaButton = findViewById(R.id.chicagoPizzaButton);
////        Button nyPizzaButton = findViewById(R.id.nyPizzaButton);
////        Button currentOrderButton = findViewById(R.id.currentOrderButton);
////        Button storeOrderButton = findViewById(R.id.storeOrderButton);
//
//        //initialize ChicagoStyleActivity
//        chicagoPizzaButton.setOnClickListener(view -> {
//            Intent makeChicagoPizza = new Intent(MainActivity.this, ChicagoPizzaActivity.class);
//            startActivity(makeChicagoPizza);
//        });
//
////        //initialize NYStyleActivity
////        newYorkButton.setOnClickListener(view -> {
////            Intent makeChicagoPizza = new Intent(MainActivity.this, NYStyleActivity.class);
////            startActivity(makeChicagoPizza);
////        });
////
////        //initialize CurrentOrderActivity
////        currentOrderButton.setOnClickListener(view -> {
////            Intent checkCurrentOrder = new Intent(MainActivity.this, CurrentOrderActivity.class);
////            startActivity(checkCurrentOrder);
////        });
////
////        //initialize StoreOrdersActivity
////        storeOrderButton.setOnClickListener(view -> {
////            Intent checkStoreOrders = new Intent(MainActivity.this, StoreOrdersActivity.class);
////            startActivity(checkStoreOrders);
////        });
//    }
}