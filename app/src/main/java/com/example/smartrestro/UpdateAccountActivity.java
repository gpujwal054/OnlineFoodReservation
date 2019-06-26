package com.example.smartrestro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.UUID;

public class UpdateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    EditText newUserName, newUserEmail, newUserAddress, newUserContact;
    private ProgressBar progressbar;
    private FirebaseAuth firebaseAuth;
    StorageReference mStorage;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    Button updateAccount,uploadProfilePic;
    private Uri imagePath;
    private static final int PICK_IMAGE_REQUEST = 100;
    ImageView updateProfilePic;
    String s;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                updateProfilePic.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        firebaseAuth = firebaseAuth.getInstance();
        newUserName = findViewById(R.id.newEditTextName);
        newUserEmail = findViewById(R.id.newEditTextEmail);
        newUserAddress = findViewById(R.id.newEditTextAddress);
        newUserContact = findViewById(R.id.newEditTextContact);
        updateAccount = findViewById(R.id.btnUpdateMyAccount);
        progressbar = findViewById(R.id.progressBar);
        firebaseStorage = FirebaseStorage.getInstance();
        progressbar.setVisibility(View.GONE);
        mStorage = FirebaseStorage.getInstance().getReference();
        updateProfilePic = findViewById(R.id.newImgView);
        uploadProfilePic = findViewById(R.id.btnNewUpload);
        uploadProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfileImage();
            }

            private void uploadProfileImage() {
                if(imagePath != null)
                {
                    final ProgressDialog progressDialog = new ProgressDialog(UpdateAccountActivity.this);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    final StorageReference ref = mStorage.child("images/"+ UUID.randomUUID().toString());
                    ref.putFile(imagePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();
                                    Toast.makeText(UpdateAccountActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            s= uri.toString().trim();
                                            Toast.makeText(UpdateAccountActivity.this,s, Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(UpdateAccountActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        });

        updateProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
            }
        });

        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                newUserName.setText(user.getName());
                newUserEmail.setText(user.getEmail());
                newUserAddress.setText(user.getAddress());
                newUserContact.setText(user.getContact());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newUserName.getText().toString().trim();
                String email = newUserEmail.getText().toString().trim();
                String address = newUserAddress.getText().toString().trim();
                String contact = newUserContact.getText().toString().trim();

                User user = new User(name,email,address,contact,s);
                databaseReference.setValue(user);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {

    }
}
