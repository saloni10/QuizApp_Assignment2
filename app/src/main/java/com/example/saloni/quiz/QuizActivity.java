package com.example.saloni.quiz;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {


    private static final String KEY_TEXT_VALUE = "text";
    Random r = new Random();
    int num;

    @Override
    public View findViewById(@IdRes int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView question = (TextView) findViewById(R.id.question_text_view);
        //Saving state on orientation change
        if (savedInstanceState != null) {
            CharSequence savedText = savedInstanceState.getCharSequence(KEY_TEXT_VALUE);
            question.setText(savedText);
            num = savedInstanceState.getInt("number");
        } else {
            num = r.nextInt(1000) + 1;
            question.setText(getString(R.string.is) + " " + num + " " + getString(R.string.prime));
        }

    }


    /*
         *Function Called On Clicking Next Button
         * Generates new question
         * Generates new random number
     */
    public void next(View view) {
        TextView question = (TextView) findViewById(R.id.question_text_view);
        num = r.nextInt(1000) + 1;
        question.setText(getString(R.string.is) + " " + num + " " + getString(R.string.prime));
    }


    /*
       *Function Called on Clicking Yes Button
     */
    public void yes(View view) {
        checktrue(num);
    }


    /*
      *Function Called on Clicking No Button
    */
    public void no(View view) {
        checkfalse(num);
    }


    /*
      *Checks if number is prime on click of Yes Button
      * Displays appropriate Toast
     */
    public void checktrue(int n) {

        if (n == 1)
            Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
        else {
            int flag = 1;
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    flag = 0;
                    break;
                }
            }

            if (flag == 1)
                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();

        }
    }

    /*
     *Checks if number is prime on click of No Button
     * Displays appropriate Toast
    */
    public void checkfalse(int n) {

        if (n == 1)
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
        else {
            int flag = 1;
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1)
                Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();//generating toast
            else
                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();

        }
    }

    /*
      * Saves State on orientation change
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView question = (TextView) findViewById(R.id.question_text_view);
        outState.putCharSequence(KEY_TEXT_VALUE, question.getText());
        outState.putInt("number", num);

    }

}
