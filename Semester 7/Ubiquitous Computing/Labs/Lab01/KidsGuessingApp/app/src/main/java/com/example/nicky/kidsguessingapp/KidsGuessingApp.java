package com.example.nicky.kidsguessingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KidsGuessingApp extends AppCompatActivity {

    int score = 0;
    TextView textView;
    Button button01, button02;
    int random01, random02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_guessing_app);

        textView = (TextView)findViewById(R.id.textView);
        button01 = (Button)findViewById(R.id.button01);
        button02 = (Button)findViewById(R.id.button02);

        textView.setText("Score: " + score);
        random01 = (int) Math.floor((Math.random() * 10) + 1);
        random02 = (int) Math.floor((Math.random() * 10) + 1);
        button01.setText(""+random01);
        button02.setText(""+random02);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(random01 > random02) {
                    score++;
                    textView.setText("Score: " + score);
                }
                random01 = (int) Math.floor((Math.random() * 10) + 1);
                random02 = (int) Math.floor((Math.random() * 10) + 1);
                button01.setText(""+random01);
                button02.setText(""+random02);
            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(random02 > random01){
                    score++;
                    textView.setText("Score: " + score);
                }
                random01 = (int) Math.floor((Math.random() * 10) + 1);
                random02 = (int) Math.floor((Math.random() * 10) + 1);
                button01.setText(""+random01);
                button02.setText(""+random02);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kids_guessing_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
