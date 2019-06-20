package com.example.smartrestro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText email,password;
    Button btnLogin,btnRegister;
    TextView frgtPass;
    private ProgressBar progressbar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);
        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.GONE);
        frgtPass = findViewById(R.id.textViewFogPass);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query query = databaseReference.child("users").orderByChild("email").equalTo(email.getText().toString().trim());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // dataSnapshot is the "issue" node with all children with id 0

                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"
                                User usersBean = user.getValue(User.class);

                                if (usersBean.password.equals(password.getText().toString().trim())) {
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Password is wrong", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email.getText().toString();
                String pass = password.getText().toString();
                if (emailId.isEmpty()) {
                    email.setError("Provide your Email first!");
                    email.requestFocus();
                } else if (pass.isEmpty()) {
                    password.setError("Enter Password!");
                    password.requestFocus();
                } else if (emailId.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailId.isEmpty() && pass.isEmpty())) {
                    progressbar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(emailId, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            progressbar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                } else {
                                    Toast.makeText(MainActivity.this, "Please verify your email address", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(I);
            }
        });
        frgtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId = email.getText().toString();

                if(TextUtils.isEmpty(emailId)){
                    Toast.makeText(getApplicationContext(),"Please fill e-mail",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(emailId)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Password reset link was sent your email address",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Mail sending error",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
