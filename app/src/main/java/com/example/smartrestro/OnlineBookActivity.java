package com.example.smartrestro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.TextView;

import java.util.Objects;


public class OnlineBookActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView name,address,contact,checkInDate,checkOutDate,checkInTime,checkOutTime,numberOfPeople;
    Button btnBookT,btnChooseT;

    ProgressBar progressbar;
    public Button bookTable;
    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_book);
         name = findViewById(R.id.eTName);
         address=findViewById(R.id.eTAddress);
         contact =findViewById(R.id.eTPhoneN);
        name = findViewById(R.id.eTName);
        address = findViewById(R.id.eTAddress);
        contact = findViewById(R.id.etContact);
        checkInDate = findViewById(R.id.eTCheckInD);

        checkInTime = findViewById(R.id.eTCheckInT);

        numberOfPeople = findViewById(R.id.eTNumberOfP);

        firebaseAuth = FirebaseAuth.getInstance();
        btnChooseT = findViewById(R.id.btnChooseTable);

        btnChooseT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(OnlineBookActivity.this, BookTableActivity.class);
                startActivity(I);
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        btnBookT.setOnClickListener(new View.OnClickListener() {
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

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(OnlineBookActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OnlineBookActivity.this, IndexActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
            Intent intent=new Intent(OnlineBookActivity.this,HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_promotion) {
            Intent intent=new Intent(OnlineBookActivity.this,PromotionActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_menus) {
            Intent intent=new Intent(OnlineBookActivity.this,MenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_order) {
            Intent intent=new Intent(OnlineBookActivity.this,MyOrderActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_bookTable) {
            Intent intent=new Intent(OnlineBookActivity.this,BookTableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_onlineBookTable) {
            Intent intent=new Intent(OnlineBookActivity.this,OnlineBookActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_shipping) {
            Intent intent=new Intent(OnlineBookActivity.this,ShippingPaymentActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_aboutUs) {
            Intent intent=new Intent(OnlineBookActivity.this,AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logOut) {
            logout();
            return true;
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
        FirebaseDatabase.getInstance().getReference("order").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(onlineBook).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressbar.setVisibility(View.GONE);
                            if (task.isSuccessful()){
                                Toast.makeText(OnlineBookActivity.this,"Registration successful. Please verify your email address.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(OnlineBookActivity.this,MainActivity.class));
                            } else{
                                Toast.makeText(OnlineBookActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
            }
        });

    }
}

