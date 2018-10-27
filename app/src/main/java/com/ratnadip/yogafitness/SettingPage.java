package com.ratnadip.yogafitness;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ratnadip.yogafitness.Database.YogaDB;

import java.util.Date;

public class SettingPage extends AppCompatActivity {

    Button btnSave;
    RadioButton rdiEasy,rdiHard,rdiMedium;
    RadioGroup rdiGroup;
    TimePicker timePicker;
    ToggleButton switchAlarm;
    YogaDB yogaDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        btnSave = (Button)findViewById(R.id.btnSave);
        rdiGroup =(RadioGroup)findViewById(R.id.rdiGroup);
        rdiEasy =(RadioButton)findViewById(R.id.rdiEasy);
        rdiMedium =(RadioButton)findViewById(R.id.rdiMedium);
        rdiHard =(RadioButton)findViewById(R.id.rdiHard);
        timePicker =(TimePicker)findViewById(R.id.timePicker);
        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);

        yogaDB = new YogaDB(this);
        int mode = yogaDB.getSettingMode();
        setRadioButton(mode);

        //Event

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkoutMode();
                saveAlarm(switchAlarm.isChecked());

                Toast.makeText(SettingPage.this,"SAVED!!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    private void saveAlarm(boolean checked) {

        if(checked)
        {

            AlarmManager manger = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(SettingPage.this,AlarmNotoficationReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            //Set Time to Alarm

            java.util.Calendar calender = java.util.Calendar.getInstance();

            Date toDay = calender.getInstance().getTime();
            calender.set(toDay.getYear(),toDay.getMonth(),toDay.getDay(),timePicker.getHour(),timePicker.getMinute());
            manger.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            Log.d("DEBUG","Alarm will wake at :"+timePicker.getHour()+":"+timePicker.getMinute());

        }
        else
        {
            //Cancel Alarm

            Intent intent = new Intent(SettingPage.this,AlarmNotoficationReciever.class);
           PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            AlarmManager manger = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manger.cancel(pendingIntent);
        }
    }

    private void saveWorkoutMode() {
        int selectedID = rdiGroup.getCheckedRadioButtonId();
        if (selectedID == rdiEasy.getId())
                yogaDB.saveSettingMode(0);
        else if (selectedID == rdiMedium.getId())
                yogaDB.saveSettingMode(1);
        else if (selectedID == rdiHard.getId())
                yogaDB.saveSettingMode(2);

    }

    private void setRadioButton(int mode) {

        if (mode == 0){
            rdiGroup.check(R.id.rdiEasy);
        }
        else if (mode == 1){
            rdiGroup.check(R.id.rdiMedium);
        }
        else if (mode == 2){
            rdiGroup.check(R.id.rdiHard);

        }
    }
}
