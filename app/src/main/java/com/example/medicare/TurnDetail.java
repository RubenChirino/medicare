package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.Controllers.TurnController;
import com.example.medicare.Models.TurnModel;
import com.example.medicare.Models.UserModel;
import com.example.medicare.Utils.MedicalSpecialtyUtil;

public class TurnDetail extends AppCompatActivity {

    // Elements
    TextView medicalSpecialityTitle;
    TextView dateTextView;
    TextView timeTextView;
    Button btnMap;
    Button cancelTurnBtn;

    // DATA
    TurnModel turn;
    long turnId;
    long turnIdUser;
    String turnDate;
    String turnSpeciality;
    String turnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_detail);

        // === ELEMENTS ==
        medicalSpecialityTitle = findViewById(R.id.textView_medicalSpeciality);
        dateTextView = findViewById(R.id.textView_turnDate);
        timeTextView = findViewById(R.id.textView_turnTime);
        btnMap = findViewById(R.id.button_map);
        cancelTurnBtn = findViewById(R.id.button_cancelTurn);

        // === DATA ===
        Intent intent = getIntent();
        turnId = intent.getLongExtra("turn_id", 0);
        turnIdUser = intent.getLongExtra("turn_id_user", 0);
        turnDate = intent.getStringExtra("turn_date");
        turnSpeciality = intent.getStringExtra("turn_speciality");
        turnTime = intent.getStringExtra("turn_time");

        medicalSpecialityTitle.setText(turnSpeciality);
        dateTextView.setText(turnDate);
        timeTextView.setText(turnTime);

        // === EVENTS
        btnMap.setOnClickListener(view -> {
            TurnModel.MedicalSpecialty specialty = TurnModel.MedicalSpecialty.fromString(turnSpeciality);
            String location = MedicalSpecialtyUtil.getLocationForSpecialty(specialty);
            System.out.println("La ubicación de " + specialty + " es: " + location);

            Uri map = Uri.parse("geo:0,0?q=" + Uri.encode(location));
            Intent goToMap = new Intent(Intent.ACTION_VIEW, map);
            startActivity(goToMap);
        });


        cancelTurnBtn.setOnClickListener(view -> {
            TurnController turnCtrl = new TurnController(TurnDetail.this);
            boolean isDeleted = turnCtrl.deleteTurn(turnId);
            if (isDeleted) {
                Toast.makeText(TurnDetail.this, "The turn has been cancelled", Toast.LENGTH_LONG).show();
                Intent intentGoToHome = new Intent(TurnDetail.this, Home.class);
                startActivity(intentGoToHome);
            } else {
                Toast.makeText(TurnDetail.this, "An error occurred trying to delete the turn", Toast.LENGTH_LONG).show();
            }
        });
    }
}