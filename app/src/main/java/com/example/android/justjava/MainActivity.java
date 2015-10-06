package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    /**
     * Declaring variable QUANTITY initial value.
     */
    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the ORDER BUTTON is clicked.
     */
    public void submitOrder(View view) {

        /**
         * This code gets the user name from the Name TextView.
         */

        EditText userNameTextView = (EditText) findViewById(R.id.name_field);
        String userName = userNameTextView.getText().toString();

        /**
         * This code connects the Whipped Cream checkbox.
         */

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        /**
         * This code connects the Chocolate checkbox.
         */

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        /**
         * This code is passing the toppings state to the calculatePrice method.
         */
        int price = calculatePrice(hasWhippedCream, hasChocolate);


        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, userName + getString(R.string.order_ready));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.hello) + userName + "!\n" + getString(R.string.receipt) + "\n\n"+ getString(R.string.order_details) + ":\n" + getString(R.string.name) + ": " + userName + "\n" + getString(R.string.add_whipped_cream) +" " + hasWhippedCream + "\n" + getString(R.string.add_chocolate) + " " + hasChocolate + "\n" + getString(R.string.quantity) + ": " + quantity + "\n" + getString(R.string.your_total) + ": $" + price + " " + getString(R.string.dollars) + ".\n\n" + getString(R.string.thank_you) );

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        createOrderSummary(userName, price, hasWhippedCream, hasChocolate);

    }

    /**
     * Create summary of the order
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not the user wants Whipped Cream topping.
     * @param addChocolate    is whether or not the user wants Chocolate topping.
     */
    private String createOrderSummary(String addUserName, int price, boolean addWhippedCream, boolean addChocolate) {

        String priceMessage = "Name: " + addUserName;
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;

    }


    /**
     * @param addWhippedCream is whether or not the user wants to add Whipped Cream topping.
     * @param addChocolate    is whether or not the user wants to add Chocolate topping.
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        return basePrice * quantity;
    }

    /**
     * This method give the instructions to 'minus' and 'plus' buttons.
     */
    public void increment(View view) {
        quantity = quantity + 1;

        if (quantity > 100) {
            quantity = 100;
            Toast.makeText(MainActivity.this,
                    "You cannot order more than 100 cups of coffee.", Toast.LENGTH_LONG).show();
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity < 1) {
            quantity = 1;
            Toast.makeText(MainActivity.this,
                    "You cannot order less than 1 cup of coffee.", Toast.LENGTH_LONG).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

}

