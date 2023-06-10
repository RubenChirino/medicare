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

import com.example.medicare.Controllers.UserController;
import com.example.medicare.Data.Global;
import com.example.medicare.Models.UserModel;

import java.util.ArrayList;

public class User extends AppCompatActivity {

    // == ELEMENTS ==
    Button btnBack;

    // == VALUES ==
    String valName;
    String valLastName;
    String valAddress;
    String valBirthDate;
    String valGender;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // ==== FIND ELEMENTS ====
        btnBack = findViewById(R.id.button_back);

        EditText inputUserName = findViewById(R.id.editText_user_name);
        EditText inputUserLastName = findViewById(R.id.editText_user_last_name);
        EditText inputUserAddress = findViewById(R.id.editText_user_address);
        EditText inputUserBirthDate = findViewById(R.id.editTextDate_user_birth_date);
        Spinner spinnerUserGender = findViewById(R.id.spinner_user_gender);
        Button btnSave = findViewById(R.id.button_user_save);

        // === DATA ===
        // Global app = (Global) getApplication();
        // UserModel user = app.getUser();

        Intent intent = getIntent();
        user = (UserModel) intent.getSerializableExtra("user");
        UserController userCtrl = new UserController(User.this);

        if (user.name != null) {
            inputUserName.setText(user.name);
        }

        if (user.lastName != null) {
            inputUserLastName.setText(user.lastName);
        }

        if (user.address != null) {
            inputUserAddress.setText(user.address);
        }

        UserModel.Genders[] genders = UserModel.Genders.values();
        String[] genderOptions = new String[genders.length];
        for (int i = 0; i < genders.length; i++) {
            genderOptions[i] = genders[i].toString();
        }
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserGender.setAdapter(genderAdapter);

        if (user.gender != null) {
            UserModel.Genders gender = UserModel.Genders.valueOf(user.gender.toUpperCase());

            int position = genderAdapter.getPosition(gender.toString());

            if (position != -1) {
                spinnerUserGender.setSelection(position);
            }
        }

        if (user.birthDate != null) {
            inputUserBirthDate.setText(user.birthDate);
        }

        btnSave.setEnabled(false);

        // ==== SET EVENT ====
        inputUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valName = inputUserName.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnSave.setEnabled(isInfoUpdated());
            }
        });

        inputUserLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valLastName = inputUserLastName.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnSave.setEnabled(isInfoUpdated());
            }
        });

        inputUserAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valAddress = inputUserAddress.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnSave.setEnabled(isInfoUpdated());
            }
        });

        inputUserBirthDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valBirthDate = inputUserBirthDate.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnSave.setEnabled(isInfoUpdated());
            }
        });

        /* UserModel.Genders selectedGender = (UserModel.Genders) parent.getItemAtPosition(position);
                valGender = selectedGender.toString(); */

        spinnerUserGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valGender = parent.getItemAtPosition(position).toString();
                btnSave.setEnabled(isInfoUpdated());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnBack.setOnClickListener(view -> {
            Intent back = new Intent(User.this, Home.class);
            startActivity(back); // onBackPressed();
        });

        btnSave.setOnClickListener(view -> {
            user.name = valName;
            user.lastName = valLastName;
            user.address = valAddress;
            user.birthDate = valBirthDate;
            user.gender = valGender;
            boolean updated = userCtrl.updateUser(user);
            if (updated) {
                Toast.makeText(getApplicationContext(), "The user has been updated correctly", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "The user cannot be updated", Toast.LENGTH_SHORT).show();
            }
            btnSave.setEnabled(false);
        });
    }

    public boolean isInfoUpdated() {
        return user.name != valName || user.lastName != valLastName || user.address != valAddress || user.birthDate != valBirthDate || user.gender != valGender;
    }
}