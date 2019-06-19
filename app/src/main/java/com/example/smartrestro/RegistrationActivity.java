package com.example.smartrestro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name, email, password, address, phone;
    private ProgressBar progressbar;
    private FirebaseAuth firebaseAuth;
    StorageReference mStorage;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 25;
    Button mChoose,mUpload;
    ImageView mImageView;


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
        mStorage = FirebaseStorage.getInstance().getReference();
        mChoose = findViewById(R.id.btnChoose);
        mUpload = findViewById(R.id.btnUpload);
        mImageView = findViewById(R.id.imgView);
        mImageView.buildDrawingCache();
        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache();
        mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }

        });
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }


    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mImageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = mStorage.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
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
                    FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressbar.setVisibility(View.GONE);
                            if (task.isSuccessful()){
                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(RegistrationActivity.this,"Registration successful. Please verify your email address.", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                                        } else{
                                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
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