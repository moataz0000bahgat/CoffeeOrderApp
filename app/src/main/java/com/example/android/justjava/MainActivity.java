/**

 * IMPORTANT: Make sure you are using the correct package name.

 * This example uses the package name:

 * package com.example.android.justjava

 * If you get an error when copying this code into Android studio, update it to match teh package name found

 * in the project's AndroidManifest.xml file.

 **/



package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**

 * This app displays an order form to order coffee.

 */

public class MainActivity extends AppCompatActivity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




    }


    int quantity = 2;

    /**

     * This method is called when the order button is clicked.

     */

    public void submitOrder(View view) {
       EditText writting_text=(EditText) findViewById(R.id.writting_text);
        String writting=writting_text.getText().toString();
        CheckBox whippedCream =(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean haswhippedcream = whippedCream.isChecked();
        CheckBox chocolate =(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean haschocolate = chocolate.isChecked();

        Log.v("MainActivity", "has whipped cream"+haswhippedcream);
        int price=calculatePrice( haswhippedcream,haschocolate);
        String priceMessage=createOrderSummary(price,haswhippedcream,haschocolate,writting);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "just java Order for");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }




    private String createOrderSummary(int price,boolean haswhippedcream,boolean haschocolate,String writting){


        String pricemessage="has whipped cream "+quantity+writting;
        pricemessage +="add whipped cream " +haswhippedcream;
        pricemessage+="/n"+haschocolate+getString(R.string.Thank_you);

        return(pricemessage);

    }

    /**

     * Calculates the price of the order.

     *

     *  the number of cups of coffee ordered

     */

    private int calculatePrice(boolean haswhippedcream,boolean haschocolate){

        int price = quantity * 5;
        int basePrice=5;
        if(haswhippedcream){
            basePrice=basePrice+1;
        }

        if(haschocolate){
            basePrice=basePrice+2;

        }


        return price;
    }
    public void increment(View view) {
        if(quantity==100){
            return;
        }
        quantity = quantity + 1 ;
        displayQuantity(quantity);



    } public void decrement(View view) {
        if(quantity==1){
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }





    /**

     * This method displays the given quantity value on the screen.

     */

    private void displayQuantity(int digit) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);

        quantityTextView.setText("" + digit);

    }

    /**

     * This method displays the given price on the screen.

     */


    /**

     * This method displays the given text on the screen.

     */




}