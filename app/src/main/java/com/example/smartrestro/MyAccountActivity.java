package com.example.smartrestro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAccountActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth firebaseAuth;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    ImageView user;
    TextView name,address,email,contact;
    Button btnUpdateMyAcc,btnOrder,btnDeleteAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        user = findViewById(R.id.iVUser);
        name = findViewById(R.id.tVName);
        address = findViewById(R.id.tVAddress);
        email = findViewById(R.id.tVEmail);
        contact= findViewById(R.id.tVContact);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        //StorageReference storageReference = firebaseStorage.getReference();
        /*storageReference.child(firebaseAuth.getUid()).child("images/").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(userProfile);
            }
        });*/
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                name.setText("Name" + user.getName());
                address.setText("Email" + user.getEmail());
                email.setText("Address" + user.getAddress());
                contact.setText("Contact" + user.getContact());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnUpdateMyAcc = findViewById(R.id.btnUpdateAccount);
        btnOrder = findViewById(R.id.btnMyOrder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btnUpdateMyAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MyAccountActivity.this, UpdateAccountActivity.class);
                startActivity(I);
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(MyAccountActivity.this, UpdateAccountActivity.class);
                startActivity(I);
            }
        });
        btnDeleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(MyAccountActivity.this, UpdateAccountActivity.class);
                startActivity(I);
            }
        });
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
        Toast.makeText(MyAccountActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MyAccountActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.app_bar_search) {
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
            Intent intent=new Intent(MyAccountActivity.this,HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_promotion) {
            Intent intent=new Intent(MyAccountActivity.this,PromotionActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_menus) {
            Intent intent=new Intent(MyAccountActivity.this,MenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_order) {
            Intent intent=new Intent(MyAccountActivity.this,MyOrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_myAccount) {
            Intent intent=new Intent(MyAccountActivity.this,MyAccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_myDetails) {
            Intent intent=new Intent(MyAccountActivity.this,MyDetailsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_bookTable) {
            Intent intent=new Intent(MyAccountActivity.this,BookTableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_shipping) {
            Intent intent=new Intent(MyAccountActivity.this,ShippingPaymentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_location) {
            Intent intent=new Intent(MyAccountActivity.this,MapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_aboutUs) {
            Intent intent=new Intent(MyAccountActivity.this,AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logOut) {
            logout();
            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
