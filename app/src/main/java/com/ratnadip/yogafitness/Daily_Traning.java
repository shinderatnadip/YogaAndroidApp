package com.ratnadip.yogafitness;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ratnadip.yogafitness.Database.YogaDB;
import com.ratnadip.yogafitness.Model.Exercises;
import com.ratnadip.yogafitness.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Daily_Traning extends AppCompatActivity {

    Button btnStart;
    ImageView ex_image;
    TextView getReady,txtCountdown,txtTimer,ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;


    int ex_id=0,limit_time=0;
    List<Exercises> list = new ArrayList<>();

    YogaDB yogaDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__traning);

        initData();

        yogaDb = new YogaDB(this);



            getReady =(TextView)findViewById(R.id.getReady);
            btnStart = (Button)findViewById(R.id.btnStart);
        ex_image =(ImageView)findViewById(R.id.detail_image);
        txtCountdown = (TextView)findViewById(R.id.txtCountdown);
        txtTimer =(TextView)findViewById(R.id.timer);
        ex_name = (TextView)findViewById(R.id.title);
        layoutGetReady = (LinearLayout)findViewById(R.id.layout_get_ready);

        progressBar =(MaterialProgressBar)findViewById(R.id.progressBar);

        //set Data

        progressBar.setMax(list.size());

        setExerciseInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (btnStart.getText().toString().toLowerCase().equals("start"))
               {
                   showGetReady();
                   btnStart.setText("done");
               }
                else if (btnStart.getText().toString().toLowerCase().equals("done"))
               {
                   if (yogaDb.getSettingMode() == 0)
                       exercisesEasyModeCountown.cancel();
                   else if (yogaDb.getSettingMode() ==1 )
                       exercisesMediumModeCountown.cancel();
                   else if (yogaDb.getSettingMode() == 2)
                       exercisesHardModeCountown.cancel();
                   restTimeCountown.cancel();

                   if (ex_id <list.size()){

                       showRestTime();

                       ex_id++;
                       progressBar.setProgress(ex_id);
                       txtTimer.setText("");
                   }
                   else
                       showFinished();



               }

                else {

                   if (yogaDb.getSettingMode() == 0)
                       exercisesEasyModeCountown.cancel();
                   else if (yogaDb.getSettingMode() ==1 )
                       exercisesMediumModeCountown.cancel();
                   else if (yogaDb.getSettingMode() == 2)
                       exercisesHardModeCountown.cancel();
                   restTimeCountown.cancel();

                   if (ex_id <list.size())
                       setExerciseInformation(ex_id);
                   else
                       showGetReady();

               }

            }
        });


    }

    private void showRestTime() {

        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);

        btnStart.setText("Skip");

        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        restTimeCountown.start();
        getReady.setText("REST TIME");
    }

    private void showGetReady() {

        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        getReady.setText("GET READY");
        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {
                txtCountdown.setText(""+(l-1000)/1000);
            }

            @Override
            public void onFinish() {

                showExercises();
            }
        }.start();

    }

    private void showExercises() {

        if (ex_id < list.size()){   //list size contains all exercises

            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

          if (yogaDb.getSettingMode() == 0)
                exercisesEasyModeCountown.start();
            else if (yogaDb.getSettingMode() ==1 )
              exercisesMediumModeCountown.start();
            else if (yogaDb.getSettingMode() == 2)
              exercisesHardModeCountown.start();

            // to View Name of Exercises

            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());
        }
        else {
            showFinished();
        }
    }

    private void showFinished() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setProgress(View.INVISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);
        getReady.setText("FINISHED !!!");
        txtCountdown.setText("Congratulations ! \nYou are done with todays Exercise");
        txtCountdown.setTextSize(20);

        //save workout done to DB

        yogaDb.saveDays(""+ Calendar.getInstance().getTimeInMillis());
    }

    CountDownTimer exercisesEasyModeCountown = new CountDownTimer(Common.TIME_LIMIT_EASY,1000) {
            @Override
            public void onTick(long l) {

                txtTimer.setText(""+(l/1000));
            }

            @Override
            public void onFinish() {

                if (ex_id < list.size()-1){
                    ex_id++;
                    progressBar.setProgress(ex_id);
                    txtTimer.setText("");

                    setExerciseInformation(ex_id);
                    btnStart.setText("Start");
                }
                else
                {
                    showFinished();
                }
            }
        };
    CountDownTimer exercisesMediumModeCountown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM,1000) {
        @Override
        public void onTick(long l) {

            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {

            if (ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else
            {
                showFinished();
            }
        }
    };
    CountDownTimer exercisesHardModeCountown = new CountDownTimer(Common.TIME_LIMIT_HARD,1000) {
        @Override
        public void onTick(long l) {

            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {

            if (ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else
            {
                showFinished();
            }
        }
    };


    CountDownTimer restTimeCountown = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long l) {

            txtCountdown.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {

           setExerciseInformation(ex_id);
            showExercises();
            }

    };


    private void setExerciseInformation(int id) {

        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);
    }

    private void initData() {

        list.add(new Exercises(R.drawable.easy_pose,"Easy Pose"));
        list.add(new Exercises(R.drawable.boat_pose,"Boat Pose"));
        list.add(new Exercises(R.drawable.bow_pose,"Bow Pose"));
        list.add(new Exercises(R.drawable.cobra_pose,"Cobra Pose"));
        list.add(new Exercises(R.drawable.crescent_lunge,"Crescent Pose"));
        list.add(new Exercises(R.drawable.downward_facing_dog,"Downward Facing Pose"));
        list.add(new Exercises(R.drawable.half_pigeon,"Half Pigeon Pose"));
        list.add(new Exercises(R.drawable.low_lunge,"Low Lunge Pose"));
        list.add(new Exercises(R.drawable.upward_bow,"Upward Pose"));
        list.add(new Exercises(R.drawable.warrior_pose,"Warrior Pose"));
        list.add(new Exercises(R.drawable.warrior_pose_2,"Warrior Pose 2"));


    }

}
