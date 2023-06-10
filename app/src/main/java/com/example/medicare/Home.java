package com.example.medicare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.Controllers.TurnController;
import com.example.medicare.Data.Global;
import com.example.medicare.Models.UserModel;
import com.example.medicare.ui.user.UserFragment;
import com.example.medicare.ui.user.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    // Elements
    RecyclerView recyclerViewElement;

    // Data
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
        /* binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_user)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // === FIND ELEMENTS ===
        TextView navbarEmailText = findViewById(R.id.textView_navbar_user_email);
        FloatingActionButton newTurnBtn = findViewById(R.id.new_turn);
        recyclerViewElement = findViewById(R.id.recyclerView);

        // ==== Set Values ====
        // Global app = (Global) getApplication();
        // UserModel user = app.getUser();

        Intent intent = getIntent();
        user = (UserModel) intent.getSerializableExtra("user");

        // navbarEmailText.setText(user.email);

        /* TurnController turnCtrl = new TurnController(Home.this);
        turnCtrl.getAllTurnByUser(user.id); */

        // ==== SET EVENTS ====
        newTurnBtn.setOnClickListener(v -> {
            Intent nextIntent = new Intent(Home.this, NewTurn.class);
            nextIntent.putExtra("user", user);
            startActivity(nextIntent);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                Intent intentGoToHomeActivity = new Intent(Home.this, Home.class);
                intentGoToHomeActivity.putExtra("user", user);
                startActivity(intentGoToHomeActivity);
            } else if (itemId == R.id.nav_user) {
                Intent intentGoToUserActivity = new Intent(Home.this, User.class);
                intentGoToUserActivity.putExtra("user", user);
                startActivity(intentGoToUserActivity);
            }
            // Close Drawer
            // DrawerLayout drawer1 = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

            return true;
        });
    }

//    public void displayData() {
//        TurnController turnCtrl = new TurnController(Home.this);
//        Cursor cursor = turnCtrl.getAllTurnByUser(user.id);
//        if (cursor.getCount() == 0) {
//            Toast.makeText(this, "No turns", Toast.LENGTH_SHORT).show();
//        } else {
//            while (cursor.moveToNext()) {
//
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}