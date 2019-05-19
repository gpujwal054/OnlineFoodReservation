package com.example.smartrestro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {
    TextView txt;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        txt = findViewById(R.id.textViewSmartRestro);
        progressBar = findViewById(R.id.progressBarIndex);
        progressBar.setVisibility(View.GONE);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent I = new Intent(IndexActivity.this, MainActivity.class);
                startActivity(I);
            }
        });
    }
}
