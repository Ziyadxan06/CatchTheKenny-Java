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

import com.ziyad.ctachthekenny.databinding.ActivityMain2Binding;
import com.ziyad.ctachthekenny.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    int score2;
    ImageView[] imageArray2;
    Runnable runnable2;
    Handler handler2;
    SQLiteDatabase database;
    String playerName;
    int levelValue;
    int difficultyLevel;

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        imageArray2 = new ImageView[] {binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5,binding.imageView6, binding.imageView7,binding.imageView8, binding.imageView9};

        score2 = 0;

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
                binding.timeText2.setText("Vaxt: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                binding.timeText2.setText("Vaxt Bitdi");
                handler2.removeCallbacks(runnable2);
                for(ImageView image : imageArray2){
                    image.setVisibility(View.INVISIBLE);
                }

                saveData();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);

                alert.setTitle("Skorunuz: " + score2);
                alert.setMessage("Yeniden oynamaq isteyirsiniz?");
                alert.setPositiveButton("Beli", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity2.this, MainMenu2.class);
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
            database = MainActivity2.this.openOrCreateDatabase("Scores",MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS scores (id INTEGER PRIMARY KEY, playername VARCHAR, playerscore DOUBLE)");
            String sqlString = "INSERT INTO scores (playername, playerscore) VALUES(?, ?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1, playerName);
            sqLiteStatement.bindDouble(2, score2);
            sqLiteStatement.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void increaseScore2(View view){
        score2++;
        binding.scoretext2.setText("Skor: " + score2);
    }

    public void hideImages(){

        handler2 = new Handler();

        runnable2 = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray2){
                    image.setVisibility(View.INVISIBLE);
                }

                Random r = new Random();
                int i = r.nextInt(9);
                imageArray2[i].setVisibility(View.VISIBLE);

                handler2.postDelayed(this, difficultyLevel);
            }
        };

        handler2.post(runnable2);

    }
}