package com.example.smartrestro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.snapshot.Index;

public class IndexActivity extends AppCompatActivity {
    private static int WELCOME_TIMEOUT =4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(IndexActivity.this,MainActivity.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        }, WELCOME_TIMEOUT);
    }
}
