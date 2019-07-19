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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                Intent intent=new Intent(AboutUsActivity.this,HomeActivity.class);
                startActivity(intent);
            } else if (id == R.id.navigation_aboutUs) {
                Intent intent=new Intent(AboutUsActivity.this,AboutUsActivity.class);
                startActivity(intent);
            } else if (id == R.id.navigation_bookTable) {
                Intent intent=new Intent(AboutUsActivity.this,BookTableActivity.class);
                startActivity(intent);
            }
            return true;
        }
    };
    FirebaseAuth firebaseAuth;
    ImageView image;
    TextView moto, address,email,contact, open,close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        image= findViewById(R.id.iVImage);
        moto = findViewById(R.id.tVMoto);
        address = findViewById(R.id.tVAddress);
        email = findViewById(R.id.tVEmail);
        contact = findViewById(R.id.tVContact);
        open = findViewById(R.id.tVOpen);
        close = findViewById(R.id.tVClose);
        firebaseAuth = FirebaseAuth.getInstance();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
