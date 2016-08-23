package com.example.saloni.quiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
    private static final String KEY_TEXT_VALUE = "text";
    int num;
    int hint_taken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        //Saves state on Orientation Change
        if (savedInstanceState != null) {
            CharSequence savedText = savedInstanceState.getCharSequence(KEY_TEXT_VALUE);
            TextView mhint_text = (TextView) findViewById(R.id.hint_text);
            mhint_text.setText(savedText);
            num = savedInstanceState.getInt("number");
            hint_taken = savedInstanceState.getInt("hintflag");
        }
    }

    /**
     * Function called on Clicking Show Hint Button
     * Displays a hint
     */
    public void show_hint(View view) {
        hint_taken = 1;
        Intent intent = getIntent();
        num = intent.getIntExtra("value", 0);
        TextView mhint_text = (TextView) findViewById(R.id.hint_text);
        int i;
        int flag=1;
       for(i=2; i<=num/2;i++)
       {
           if(num%i==0)
           {    flag=0;
               break;}
       }
        if(flag==0)
     mhint_text.setText("Check for divisibility of " + num + " by "+ i);
        else
            mhint_text.setText("No factors of " + num+" exist");


    }

    /**
     * Sends data back to the main activity
     */
    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("TakenOrNot", hint_taken);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView mhint_text = (TextView) findViewById(R.id.hint_text);
        outState.putCharSequence(KEY_TEXT_VALUE, mhint_text.getText());
        outState.putInt("number", num);
        outState.putInt("hintflag", hint_taken);

    }
}
