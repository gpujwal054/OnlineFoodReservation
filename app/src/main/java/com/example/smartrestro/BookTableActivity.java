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
import android.widget.Toast;

public class BookTableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);
        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
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
        Toast.makeText(BookTableActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(BookTableActivity.this, IndexActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book_table, menu);
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
            Intent intent=new Intent(BookTableActivity.this,HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_promotion) {
            Intent intent=new Intent(BookTableActivity.this,PromotionActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_menus) {
            Intent intent=new Intent(BookTableActivity.this,MenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_order) {
            Intent intent=new Intent(BookTableActivity.this,MyOrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_myAccount) {
            Intent intent=new Intent(BookTableActivity.this,MyAccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_myDetails) {
            Intent intent=new Intent(BookTableActivity.this,MyDetailsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_bookTable) {
            Intent intent=new Intent(BookTableActivity.this,BookTableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_shipping) {
            Intent intent=new Intent(BookTableActivity.this,ShippingPaymentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_location) {
            Intent intent=new Intent(BookTableActivity.this,MapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_aboutUs) {
            Intent intent=new Intent(BookTableActivity.this,AboutUsActivity.class);
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
