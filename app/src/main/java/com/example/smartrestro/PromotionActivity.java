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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PromotionActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                Intent intent=new Intent(PromotionActivity.this,HomeFragment.class);
                startActivity(intent);
            } else if (id == R.id.navigation_table) {
                Intent intent=new Intent(PromotionActivity.this,TableFragment.class);
                startActivity(intent);
            } else if (id == R.id.navigation_menu) {
                Intent intent=new Intent(PromotionActivity.this,MenuFragment.class);
                startActivity(intent);
            } else if (id == R.id.navigation_cart) {
                Intent intent=new Intent(PromotionActivity.this,SearchFragment.class);
                startActivity(intent);
            }  else if (id == R.id.navigation_payment) {
                Intent intent=new Intent(PromotionActivity.this,ShippingPaymentFragment.class);
                startActivity(intent);
            }
            return true;
        }
    };
    FirebaseAuth firebaseAuth;
    ImageView wines ,drinks;
    TextView twines, tdrinks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        firebaseAuth = FirebaseAuth.getInstance();
       /* wines = findViewById(R.id.iVWines);
        drinks = findViewById(R.id.iVDrinks);
        twines = findViewById(R.id.tVWines);
        tdrinks = findViewById(R.id.tVDrinks);*/
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
