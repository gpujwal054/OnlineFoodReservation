package com.example.smartrestro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, email, password, address, phone;
    private ProgressBar progressbar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = firebaseAuth.getInstance();
        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        address = findViewById(R.id.editTextAddress);
        phone = findViewById(R.id.editTextContact);
        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.GONE);
        findViewById(R.id.buttonRegister).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
        }
    }
    private void registerUser(){
        final String username = name.getText().toString().trim();
        final String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();
        final String user_address = address.getText().toString().trim();
        final String user_contact = phone.getText().toString().trim();

        if (username.isEmpty()){
            name.setError("Provide your user name");
            name.requestFocus();
            return;
        }

        if (user_email.isEmpty()){
            email.setError("Provide your email");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){
            email.setError("Invalid email format");
            email.requestFocus();
            return;
        }

        if (user_password.isEmpty()){
            password.setError("Provide your password");
            password.requestFocus();
            return;
        }

        if (user_password.length() < 6){
            password.setError("Minimum password length must be with six digit");
            password.requestFocus();
            return;
        }

        if (user_address.isEmpty()){
            address.setError("Provide your address");
            address.requestFocus();
            return;
        }

        if (user_contact.isEmpty()){
            phone.setError("Provide your contact number");
            phone.requestFocus();
            return;
        }

        if (user_contact.length() !=10){
            phone.setError("Invalid phone number");
            phone.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(username,user_email,user_address,user_contact);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressbar.setVisibility(View.GONE);
                            if (task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this,"Registration successful", Toast.LENGTH_LONG).show();
                            } else {
                                // Display error message
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegistrationActivity.this,"Registration failure", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonRegister:
                registerUser();
                break;
        }
    }
}