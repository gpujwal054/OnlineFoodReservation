package com.example.smartrestro;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MyAccountActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView logOut;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        firebaseAuth = FirebaseAuth.getInstance();
        logOut = findViewById(R.id.textViewLogOut);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

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
        } else if (id == R.id.nav_shipping) {
            Intent intent=new Intent(MyAccountActivity.this,ShippingPaymentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_aboutUs) {
            Intent intent=new Intent(MyAccountActivity.this,AboutUsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
