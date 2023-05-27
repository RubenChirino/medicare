package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicare.Controllers.UserController;
import com.example.medicare.Helpers.PasswordHelper;
import com.example.medicare.Models.UserModel;

public class SignIn extends AppCompatActivity {

    // Elements
    EditText inputEmail;
    EditText inputPassword;
    EditText inputConfirmPassword;
    Button btnSignIn;

    // Data
    String valEmail;
    String valPassword;
    String valConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Inputs
        inputEmail = findViewById(R.id.editText_email_signIn);
        inputPassword = findViewById(R.id.editText_password_signIn);
        inputConfirmPassword = findViewById(R.id.editText_confirm_password);

        // Buttons
        btnSignIn = findViewById(R.id.button_signIn);

        // === EVENTS ===
        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valEmail = inputEmail.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnSignIn.setEnabled(!areInputsEmpty());
            }
        });

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valPassword = inputPassword.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnSignIn.setEnabled(!areInputsEmpty());
            }
        });

        inputConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valConfirmPassword = inputConfirmPassword.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnSignIn.setEnabled(!areInputsEmpty());
            }
        });

        btnSignIn.setOnClickListener(view -> {

            try {
                UserController userCtrl = new UserController(SignIn.this);

                // === VALIDATIONS ===
                if (valEmail == null || valEmail.length() == 0) {
                    Toast.makeText(SignIn.this, "You need to write a email.", Toast.LENGTH_LONG).show();
                    return;
                }

                if ((valPassword == null || valPassword.length() == 0) || (valConfirmPassword == null || valConfirmPassword.length() == 0) ) {
                    Toast.makeText(SignIn.this, "You need to write the passwords.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!valPassword.equals(valConfirmPassword)) {
                    Toast.makeText(SignIn.this, "Passwords do not match.", Toast.LENGTH_LONG).show();
                    return;
                }

                String encryptedPass = PasswordHelper.encrypt(valPassword, valEmail);

                UserModel userMdl = new UserModel(valEmail, encryptedPass);
                long id = userCtrl.addUser(userMdl);

                // Go to Home
                if (id > 0) {
                    cleanInputs();
                    Intent intent = new Intent(SignIn.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignIn.this, "Error in the new user creation.", Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex) {
                ex.toString();
            }
        });
    }

    private Boolean areInputsEmpty() {
        Boolean isEmailEmpty = inputEmail.getText().toString().isEmpty();
        Boolean isPasswordEmpty = inputPassword.getText().toString().isEmpty();
        Boolean isConfirmPasswordEmpty = inputConfirmPassword.getText().toString().isEmpty();
        return isEmailEmpty && isPasswordEmpty && isConfirmPasswordEmpty;
    }

    private void cleanInputs() {
        inputEmail.setText("");
        inputPassword.setText("");
        inputConfirmPassword.setText("");
    }
}