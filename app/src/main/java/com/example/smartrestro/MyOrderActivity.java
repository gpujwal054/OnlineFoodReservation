package com.example.smartrestro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

public class MyOrderActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                Intent intent=new Intent(MyOrderActivity.this,HomeFragment.class);
                startActivity(intent);
            } else if (id == R.id.navigation_menu) {
                Intent intent=new Intent(MyOrderActivity.this,MenuFragment.class);
                startActivity(intent);
            } else if (id == R.id.navigation_search) {
                Intent intent=new Intent(MyOrderActivity.this,SearchFragment.class);
                startActivity(intent);
            } else if (id == R.id.navigation_table) {
                Intent intent=new Intent(MyOrderActivity.this,TableFragment.class);
                startActivity(intent);
            } else if (id == R.id.navigation_payment) {
                Intent intent=new Intent(MyOrderActivity.this,ShippingPaymentFragment.class);
                startActivity(intent);
            }
            return true;
        }
    };
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        firebaseAuth = FirebaseAuth.getInstance();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
