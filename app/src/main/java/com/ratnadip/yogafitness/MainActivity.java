package com.ratnadip.yogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnCalender,btnExercise,btnSetting,btnAbout;
    ImageView btnTraning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTraning = (ImageView)findViewById(R.id.btnTraining);

        btnTraning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Daily_Traning.class);
                startActivity(intent);
            }
        });

        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
            }
        });

        btnCalender = (Button)findViewById(R.id.btnCalender);
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Calendar.class);
                startActivity(intent);
            }
        });

        btnSetting =(Button)findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SettingPage.class);
                startActivity(intent);
            }
        });

        btnExercise = (Button)findViewById(R.id.btnExercises);
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListExercises.class);
                startActivity(intent);
            }
        });

    }
}
