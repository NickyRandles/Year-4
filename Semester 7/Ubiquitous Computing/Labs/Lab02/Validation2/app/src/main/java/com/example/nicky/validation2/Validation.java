package com.example.nicky.validation2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation extends AppCompatActivity {

    EditText name, password, number, email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        number = (EditText)findViewById(R.id.number);
        email = (EditText)findViewById(R.id.email);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameEnter = name.getText().toString();
                String passwordEnter = password.getText().toString();
                String numberEnter = number.getText().toString();
                String emailEnter = email.getText().toString();

                if (!validateName(nameEnter)) {
                    name.setError("Invalid name");
                    name.requestFocus();
                } else if (!validatePassword(passwordEnter)) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else if (!validateNumber(numberEnter)) {
                    number.setError("Invalid number");
                    number.requestFocus();
                } else if (!validateEmail(emailEnter)) {
                    email.setError("Invalid email");
                    email.requestFocus();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Confirmation.class);
                    intent.putExtra("name", nameEnter);
                    startActivity(intent);
                }

            }


        });


    }

    private boolean validateName(String name) {
        String regExp = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();

    }

    private boolean validatePassword(String password) {
        String regExp = "^[a-zA-Z0-9 ]+$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private boolean validateNumber(String number) {
        String regExp = "^[0-9 ]+$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }

    private boolean validateEmail(String nameEmail) {
        String regExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(nameEmail);

        return matcher.matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
