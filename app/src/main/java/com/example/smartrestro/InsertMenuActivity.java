package com.example.smartrestro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class InsertMenuActivity extends AppCompatActivity {
    EditText prod_name,prod_price;
    ImageView menuImage;
    Button chooseImage,uploadImage,insertProduct;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference databaseMenuDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_menu);
        prod_name = findViewById(R.id.eTProductName);
        prod_price = findViewById(R.id.eTProductPrice);
        menuImage = findViewById(R.id.imgMenu);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        chooseImage = findViewById(R.id.btnChooseImage);
        uploadImage = findViewById(R.id.btnUploadImage);
        insertProduct = findViewById(R.id.btnInsert);
        databaseMenuDetails = FirebaseDatabase.getInstance().getReference("menu-item");
        insertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProductData();
            }
        });
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
                menuImage.setImageBitmap(bitmap);
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

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(InsertMenuActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(InsertMenuActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void sendProductData(){
        String productName = prod_name.getText().toString().trim();
        String productPrice = prod_price.getText().toString();
        String productImage = filePath.getPath();

        if (!TextUtils.isEmpty(productName) && !TextUtils.isEmpty(productPrice) && !TextUtils.isEmpty(productImage)){
            String id = databaseMenuDetails.push().getKey();
            Menu menu = new Menu(id,productName,productPrice,productImage);
            databaseMenuDetails.child(id).setValue(menu);
            Toast.makeText(this,"Menu inserted successfully",Toast.LENGTH_LONG).show();
        } else {
            if (TextUtils.isEmpty(productName)){
                Toast.makeText(this,"Insert menu name",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(productPrice)){
                Toast.makeText(this,"Insert menu price",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Upload image!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
