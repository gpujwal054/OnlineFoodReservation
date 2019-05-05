package com.example.smartrestro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users");
    private Button mButtonRegister;
    EditText mName,mEmail,mAddress,mContact,mPassword,mConfPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myRef.setValue("https://smart-restro-232a3.firebaseio.com/Users");

        mName=findViewById(R.id.editTextName);
        mEmail=findViewById(R.id.editTextEmail);
        mAddress=findViewById(R.id.editTextAddress);
        mContact=findViewById(R.id.editTextContact);
        mPassword=findViewById(R.id.editTextPassword);
        mConfPass=findViewById(R.id.editTextConfPassword);
        mButtonRegister=findViewById(R.id.buttonRegister);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString();
                DatabaseReference mChildRef=myRef.child("Name");
                mChildRef.setValue(name);
            }

        });
    }
}
