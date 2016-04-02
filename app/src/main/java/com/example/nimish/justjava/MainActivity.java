package com.example.nimish.justjava;


        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.text.Editable;
        import android.util.Log;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox= (CheckBox) findViewById(R.id.notify_me_checkbox);
       /* if (((CheckBox) view).isChecked()) {
            Toast.makeText(MainActivity.this,
                    "whipped cream added:)", Toast.LENGTH_LONG).show();

        }*/
        boolean hasWhippedcream=whippedCreamCheckBox.isChecked();
        Log.v("main activity","has whipped cream:"+hasWhippedcream);
        String msg;
if(hasWhippedcream)
{
    msg=createOrderSummary()+"Cost of "+display(quantity)+" is "+displayPrice(22 * quantity)+ "$";

}
       else {
    msg = createOrderSummary()+"Cost of " + display(quantity) + " is " + displayPrice(20 * quantity) + "$";
}
        display(quantity);
       // displayPrice(2 * quantity);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "nimishjain1000@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your coffee summary");
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        startActivity(Intent.createChooser(intent, "Send Email"));
       // displayMessage(msg);
    }
    private void displayMessage(String msg)
    {
        TextView priceTextView=(TextView) findViewById(R.id.price_text_view);
    priceTextView.setText(msg);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private int display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        return number;
    }
    public void inc(View view) {
         quantity++;
        display(quantity);

    }
    public void dec(View view) {

        if (quantity >0) {
            quantity--;
            display(quantity);
        }
        else
        {  Context context = getApplicationContext();
            CharSequence text = "coffee cannot be negative!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
    boolean isChecked=false;
    public String check()
    {
        EditText editText = (EditText) findViewById(R.id.name);
        String value = editText.getText().toString();
        return value;

    }
    private int displayPrice(int number) {
        //TextView priceTextView = (TextView) findViewById(
          //      R.id.price_text_view);
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        return number;
    }
    private String createOrderSummary()
    {
        String a="Hello "+check()+",your order summary is";
        a+="Quantity "+display(quantity);
        return a;
    }
}
