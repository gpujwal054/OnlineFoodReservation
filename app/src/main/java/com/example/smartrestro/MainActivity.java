package com.example.smartrestro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRef.setValue("https://smart-restro-232a3.firebaseio.com/");
    }
    public void getLogin(View v){
        Intent intent=new Intent(this,NavigationActivity.class);
        startActivity(intent);
    }
    public void getRegister(View v){
        Intent intent=new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
}
