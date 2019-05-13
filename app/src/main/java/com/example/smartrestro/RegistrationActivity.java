package com.example.smartrestro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    public EditText email, password;
    Button btnRegister;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = firebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email.getText().toString();
                String pass = password.getText().toString();
                if (emailId.isEmpty()) {
                    email.setError("Provide your Email first!");
                    email.requestFocus();
                } else if (pass.isEmpty()) {
                    password.setError("Set your password");
                    password.requestFocus();
                } else if (emailId.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailId.isEmpty() && pass.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailId, pass).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
