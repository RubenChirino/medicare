package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicare.Controllers.TurnController;
import com.example.medicare.Controllers.UserController;
import com.example.medicare.Models.TurnModel;
import com.example.medicare.Models.UserModel;

public class NewTurn extends AppCompatActivity {

    // Elements
    Spinner spinnerMedicalSpeciality;
    EditText textInputEditDate;
    EditText textInputEditTime;
    Button btnBack;
    Button btnCreate;

    // Values
    String valSpeciality;
    String valDate;
    String valTime;

    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_turn);

        Intent intent = getIntent();
        user = (UserModel) intent.getSerializableExtra("user");

        TurnController turnCtrl = new TurnController(NewTurn.this);

        // ==== FIND ELEMENTS ====
        spinnerMedicalSpeciality = findViewById(R.id.spinner_medicalSpeciality);
        textInputEditDate = findViewById(R.id.editTextDate_date);
        textInputEditTime = findViewById(R.id.editTextTime_time);
        btnCreate = findViewById(R.id.button_create);
        btnBack = findViewById(R.id.button_newTurn_back);

        // ==== SET DATA ====
        TurnModel.MedicalSpecialty[] specialties = TurnModel.MedicalSpecialty.values();
        String[] specialtyOptions = new String[specialties.length];
        for (int i = 0; i < specialties.length; i++) {
            specialtyOptions[i] = specialties[i].toString();
        }
        ArrayAdapter<String> specialityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specialtyOptions);
        specialityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedicalSpeciality.setAdapter(specialityAdapter);

        spinnerMedicalSpeciality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valSpeciality = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btnCreate.setEnabled(false);
            }
        });

        textInputEditDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valDate = textInputEditDate.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnCreate.setEnabled(!isInfoEmpty());
            }
        });

        textInputEditTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valTime = textInputEditTime.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnCreate.setEnabled(!isInfoEmpty());
            }
        });

        btnCreate.setEnabled(false);
        // ==== SET EVENT ====
        btnCreate.setOnClickListener(view -> {
            if (valSpeciality == null) {
                Toast.makeText(getApplicationContext(), "You need to select a medical speciality", Toast.LENGTH_SHORT).show();
                return;
            }

            if (valDate == null) {
                Toast.makeText(getApplicationContext(), "You need to select a date", Toast.LENGTH_SHORT).show();
                return;
            }

            if (valTime == null) {
                Toast.makeText(getApplicationContext(), "You need to select a time", Toast.LENGTH_SHORT).show();
                return;
            }

            TurnModel turn = new TurnModel(valSpeciality, valDate, valTime);
            long id = turnCtrl.addTurn(turn, user.id);

            if (id > 0) {
                Toast.makeText(getApplicationContext(), "The turn has been created correctly", Toast.LENGTH_SHORT).show();

                Intent intentGoToHome = new Intent(NewTurn.this, Home.class);
                startActivity(intentGoToHome);
            } else {
                Toast.makeText(getApplicationContext(), "The turn cannot be created", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    public boolean isInfoEmpty() {
        return (valSpeciality == null || valDate == null || valTime == null);
    }
}