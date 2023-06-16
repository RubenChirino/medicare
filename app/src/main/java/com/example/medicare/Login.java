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

public class Login extends AppCompatActivity {

    // Data
    String valEmail;
    String valPassword;

    // Elements
    EditText inputUserEmail;
    EditText inputUserPassword;
    Button btnGoToSignIn;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Global app = (Global) getApplication();

        // Inputs
        inputUserEmail = findViewById(R.id.input_user_email);
        inputUserPassword = findViewById(R.id.input_user_password);

        // Buttons
        btnGoToSignIn = findViewById(R.id.button_go_to_logIn);
        btnLogin = findViewById(R.id.button_login);

        btnLogin.setEnabled(false);

        // ==== Set Events ====
        inputUserEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valEmail = inputUserEmail.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnLogin.setEnabled(!inputUserEmail.getText().toString().isEmpty());
            }
        });

        inputUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                valPassword = inputUserPassword.getText().toString();
            }

            public void afterTextChanged(Editable s) {
                btnLogin.setEnabled(!inputUserPassword.getText().toString().isEmpty());
            }
        });

        btnLogin.setOnClickListener(view -> {

            UserController userCtrl = new UserController(Login.this);

            // === VALIDATIONS ===
            if (valEmail == null || valEmail.length() == 0) {
                Toast.makeText(Login.this, "You need to write a email.", Toast.LENGTH_LONG).show();
                return;
            }

            if (valPassword == null || valPassword.length() == 0) {
                Toast.makeText(Login.this, "You need to write the passwords.", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                String encryptedPass = PasswordHelper.encrypt(valPassword, valEmail);

                UserModel user = userCtrl.getUserByEmailAndPassword(valEmail, encryptedPass);
                // app.setUser(userLogged);

                if (user == null) {
                    Toast.makeText(Login.this, "The password or the email entered are incorrect.", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(Login.this, Home.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(Login.this, "Error in the login.", Toast.LENGTH_LONG).show();
            }
        });

        btnGoToSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, SignIn.class);
            startActivity(intent);
        });

    }
}