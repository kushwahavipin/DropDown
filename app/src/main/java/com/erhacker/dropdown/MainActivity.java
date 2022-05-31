package com.erhacker.dropdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText State, City, Village;
    Button Submit;
    Spinner spinnerState, spinnerCity, spinnerVillage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        State = findViewById(R.id.State);
        City = findViewById(R.id.City);
        Village = findViewById(R.id.Village);
        Submit = findViewById(R.id.Submit);
        spinnerState = findViewById(R.id.spinnerState);
        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerVillage = findViewById(R.id.spinnerVillage);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = State.getText().toString();
                String city = City.getText().toString();
                String village = Village.getText().toString();

                if (!state.isEmpty() & !city.isEmpty() & !village.isEmpty()) {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.insertLabel(state,city,village);
                    // making input filed text to blank
                    State.setText("");
                    City.setText("");
                    Village.setText("");
                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(State.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(City.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(Village.getWindowToken(), 0);
                    // loading spinner with newly added data
                    loadSpinnerData();
            } else {
                    Toast.makeText(MainActivity.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadSpinnerData() {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        spinnerState.setAdapter(dataAdapter);
//        spinnerCity.setAdapter(dataAdapter);
//        spinnerVillage.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item


        // Showing selected spinner item
//        Toast.makeText(spinnerState.getContext(), "You selected: " + state,
//                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}