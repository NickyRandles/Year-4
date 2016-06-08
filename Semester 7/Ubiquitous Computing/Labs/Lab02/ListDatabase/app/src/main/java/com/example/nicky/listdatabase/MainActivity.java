package com.example.nicky.listdatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText searchText, listText;
    DBHandler dbHandler;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = (EditText) findViewById(R.id.searchText);
        listText = (EditText) findViewById(R.id.listText);

        dbHandler = new DBHandler(this, null, null, 1);

    }

    public void createButtonClicked(View view){

        if(searchText.getText().length() > 0){
            String checkIfExists = dbHandler.searchDatabase(searchText.getText().toString());

            if(checkIfExists != ""){
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("List already exists")
                        .setTitle("Alert")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                alert.show();
            }
            else{
                list = new List(searchText.getText().toString(), listText.getText().toString());
                dbHandler.addList(list);
                searchText.setText("");
                listText.setText("");
            }

        }

        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Please enter list name first")
                    .setTitle("Alert")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            alert.show();
        }


    }

    public void retrieveButtonClicked(View view){
        String results = dbHandler.searchDatabase(searchText.getText().toString());
        if(results != ""){
            listText.setText(results);
        }
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("No list saved under this name")
                    .setTitle("Alert")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            alert.show();
        }

    }

    public void clearButtonClicked(View view){
        listText.setText("");
    }

    public void saveButtonClicked(View view){
        list = new List(searchText.getText().toString(), listText.getText().toString());

        if(searchText.getText().length() > 0){
            dbHandler.saveList(list);
            searchText.setText("");
            listText.setText("");
        }
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Please enter name to save list under")
                    .setTitle("Alert")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            alert.show();
        }

    }

    public void deleteButtonClicked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to delete this list?")
                .setTitle("Alert")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dbHandler.deleteList(searchText.getText().toString());
                        searchText.setText("");
                        listText.setText("");
                    }
                })
                .setNegativeButton("Canel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alert.show();

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
