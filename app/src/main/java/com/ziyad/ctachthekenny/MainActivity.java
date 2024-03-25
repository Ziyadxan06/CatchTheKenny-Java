package com.ziyad.ctachthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ziyad.ctachthekenny.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score;
    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;
    SQLiteDatabase database;
    String playerName;

    int difficultyLevel;
    int levelValue;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        imageArray = new ImageView[] {binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5,binding.imageView6, binding.imageView7,binding.imageView8, binding.imageView9};

        score = 0;

        Intent getLevel = getIntent();
        levelValue = getLevel.getIntExtra("levelInfo", 0);
        if(levelValue == 1){
            difficultyLevel = 500;
        }else{
            difficultyLevel = 1000;
        }


        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                binding.timeText.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                binding.timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                saveData();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Your Score: " + score);
                alert.setMessage("Do you want to play again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

                alert.show();
            }

        }.start();

        hideImages();
    }

    public void saveData(){
        Intent getInfo = getIntent();
        playerName = getInfo.getStringExtra("userName");

        try{
            database = MainActivity.this.openOrCreateDatabase("Scores",MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS scores (id INTEGER PRIMARY KEY, playername VARCHAR, playerscore DOUBLE)");
            String sqlString = "INSERT INTO scores (playername, playerscore) VALUES(?, ?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1, playerName);
            sqLiteStatement.bindDouble(2, score);
            sqLiteStatement.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void increaseScore(View view){
        score++;
        binding.scoretext.setText("Score: " + score);
    }

    public void hideImages(){

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random r = new Random();
                int i = r.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, difficultyLevel);
            }
        };

        handler.post(runnable);

    }
}