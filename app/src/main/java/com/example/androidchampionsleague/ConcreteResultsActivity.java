package com.example.androidchampionsleague;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ConcreteResultsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] matchDays= {"Matchday 1", "Matchday 2", "Matchday 3", "Matchday 4", "Matchday 5", "Matchday 6" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concrete_results);

        // Linking the Views to Java’s instances
        Spinner spin1=(Spinner) findViewById(R.id.results_spinner);
        spin1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Now, Create a Array Adapter
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item, matchDays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Assigning the adapter to Spinner
        spin1.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "You have Chosen "+matchDays[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}