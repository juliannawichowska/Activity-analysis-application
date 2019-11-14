package com.example.activity_analysis_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button historyBtn;
    Button currentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyBtn = findViewById(R.id.HistoryBtn);
        currentBtn = findViewById(R.id.CurrentBtn);

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(a);
            }
        });

        currentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,CurrentActivity.class);
                startActivity(a);
            }
        });
    }
}
