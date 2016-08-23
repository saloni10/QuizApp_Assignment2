package com.example.saloni.quiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String KEY_TEXT_VALUE = "text";

    int num;
    int cheated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        // Saves state on orientation change
        if (savedInstanceState != null) {
            CharSequence savedText = savedInstanceState.getCharSequence(KEY_TEXT_VALUE);
            TextView mcheat = (TextView) findViewById(R.id.cheat_text);
            mcheat.setText(savedText);
            num = savedInstanceState.getInt("number");
            cheated = savedInstanceState.getInt("cheatflag");
        }
    }

    /**
     * Function called on Clicking Show Answer Button
     * Displays the answer
     */
    public void show_answer(View view) {
        cheated = 1;
        Intent i = getIntent();
        num = i.getIntExtra("value", 0);
        checkprime(num);

    }

    /**
     * Checks if number is prime or not
     */
    public void checkprime(int n) {

        TextView mcheat = (TextView) findViewById(R.id.cheat_text);
        int flag = 1;
        if (n == 1) {
            mcheat.setText(n + " is not prime");
        } else {
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1)
                mcheat.setText(num + " is prime");
            else
                mcheat.setText(num + " is not prime");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView mcheat = (TextView) findViewById(R.id.cheat_text);
        outState.putCharSequence(KEY_TEXT_VALUE, mcheat.getText());
        outState.putInt("number", num);
        outState.putInt("cheatflag", cheated);

    }

    /**
     * Sends data back to the main activity
     */
    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("CheatOrNot", cheated);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
