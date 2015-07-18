package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    /**
     * Declaring Price and Quantity variables.
     */
    int quantity = 2;
    int priceOfCoffee = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the ORDER BUTTON is clicked.
     */
    public void submitOrder(View view) {
        int finalPrice = (quantity * priceOfCoffee);
        displayPrice(finalPrice);
        String priceMessage = "Your total is: " + quantity + " coffee(s).\nYou pay $" + finalPrice + " dollar(s).\nThank you for your purchase!";
        displayMessage(priceMessage);
    }

    /**
     * This method give the instructions to 'minus' and 'plus' buttons.
     */
    public void increment (View view){
        quantity = quantity + 1;
        display(quantity);
        displayPrice(quantity * priceOfCoffee);
    }

    public void decrement (View view){
        quantity = quantity - 1;
        display(quantity);
        displayPrice(quantity * priceOfCoffee);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }


}

