package com.example.medicare.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medicare.Login;
import com.example.medicare.Models.UserModel;
import com.example.medicare.R;
import com.example.medicare.SignIn;
import com.example.medicare.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;

    // == VALUES ==
    String valName;
    String valLastName;
    String valAddress;
    String valBirthDate;
    String valGender;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /* UserViewModel userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class); */

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /* final TextView textView = binding.textUser;
        userViewModel.getText().observe(getViewLifecycleOwner(), textView::setText); */

        // === FIND ===
        EditText inputUserName = root.findViewById(R.id.editText_user_name);
        EditText inputUserLastName = root.findViewById(R.id.editText_user_last_name);
        EditText inputUserAddress = root.findViewById(R.id.editText_user_address);
        EditText inputUserBirthDate = root.findViewById(R.id.editTextDate_user_birth_date);
        Spinner spinnerUserGender = root.findViewById(R.id.spinner_user_gender);
        Button btnSave = root.findViewById(R.id.button_user_save);

        // === DATA ===
        UserModel.Genders[] genders = UserModel.Genders.values();
        String[] genderOptions = new String[genders.length];
        for (int i = 0; i < genders.length; i++) {
            genderOptions[i] = genders[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, genderOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserGender.setAdapter(adapter);

        // === EVENTS ===

        // NAME
        inputUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valName = inputUserName.getText().toString();
            }

            public void afterTextChanged(Editable s) {}
        });

        // LAST NAME
        inputUserLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valLastName = inputUserLastName.getText().toString();
            }

            public void afterTextChanged(Editable s) {}
        });

        // ADDRESS
        inputUserAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valAddress = inputUserAddress.getText().toString();
            }

            public void afterTextChanged(Editable s) {}
        });

        // GENDER
        spinnerUserGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = (String) parent.getItemAtPosition(position);
                valGender = selectedGender.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // BIRTH DATE
        inputUserBirthDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valBirthDate = inputUserBirthDate.getText().toString();
            }

            public void afterTextChanged(Editable s) {}
        });

        // SAVE
        btnSave.setOnClickListener(view -> {



            // Toast.makeText(UserFragment.this, "User info updated", Toast.LENGTH_LONG).show();

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}