package com.example.smartrestro;

import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class OnlineBookActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText name, address, contact, checkInDate, checkOutDate, checkInTime, checkOutTime, numberOfPeople;
    ProgressBar progressbar;
    public Button bookTable;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_book);
        Toolbar toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.eTName);
        address = findViewById(R.id.eTAddress);
        contact = findViewById(R.id.eTPhoneN);
        checkInDate = findViewById(R.id.eTCheckInD);
        checkOutDate = findViewById(R.id.eTCheckOutD);
        //checkInTime = findViewById(R.id.eTCheckInT);
        //checkOutTime = findViewById(R.id.eTCheckOutT);
        numberOfPeople = findViewById(R.id.eTNumberOfP);
        progressbar = findViewById(R.id.progressBar);
        bookTable = findViewById(R.id.btnBookTable);
        bookTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodOrder();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.online_book, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private void foodOrder() {
        final String username = name.getText().toString().trim();
        final String user_address = address.getText().toString().trim();
        final String user_contact = contact.getText().toString().trim();
        final String user_checkIn_Date = checkInDate.getText().toString().trim();
        final String user_checkOut_Date = checkOutDate.getText().toString().trim();
        final String user_checkIn_Time = checkInTime.getText().toString().trim();
        final String user_checkOut_Time = checkOutTime.getText().toString().trim();
        final String user_number = numberOfPeople.getText().toString().trim();

        if (username.isEmpty()) {
            name.setError("Provide your user name");
            name.requestFocus();
            return;
        }

        if (user_address.isEmpty()) {
            address.setError("Provide your email");
            address.requestFocus();
            return;
        }

        if (user_contact.isEmpty()) {
            contact.setError("Provide your contact number");
            contact.requestFocus();
            return;
        }

        if (user_contact.length() != 10) {
            contact.setError("Invalid phone number");
            contact.requestFocus();
            return;
        }

        if (user_checkIn_Date.isEmpty()) {
            checkInDate.setError("Mention your check in date");
            checkInDate.requestFocus();
            return;
        }

        if (user_checkOut_Date.isEmpty()) {
            checkOutTime.setError("Mention your check out date");
            checkOutTime.requestFocus();
            return;
        }

        if (user_checkIn_Time.isEmpty()) {
            checkInTime.setError("Mention your check in time");
            address.requestFocus();
            return;
        }

        if (user_checkOut_Time.isEmpty()) {
            checkInTime.setError("Mention your check out time");
            address.requestFocus();
            return;
        }

        if (user_number.isEmpty()) {
            numberOfPeople.setError("Mention number of people");
            numberOfPeople.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        OnlineBook onlineBook = new OnlineBook(username,user_address,user_contact,user_checkIn_Date,user_checkOut_Date,user_checkIn_Time,user_checkOut_Time,user_number);
        mDatabase.child("online book").setValue(onlineBook);

    }
}

