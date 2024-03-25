package com.ziyad.ctachthekenny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ziyad.ctachthekenny.databinding.ActivityMainMenu2Binding;
import com.ziyad.ctachthekenny.databinding.ActivityMainMenuBinding;

public class MainMenu2 extends AppCompatActivity {

    private ActivityMainMenu2Binding binding;
    String playerName;
    Intent intentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenu2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.languageBtn2.setVisibility(View.INVISIBLE);
        binding.selectedLanAze2.setVisibility(View.INVISIBLE);
        binding.selectedLanEng2.setVisibility(View.INVISIBLE);
        binding.easyMode2.setVisibility(View.INVISIBLE);
        binding.hardMode2.setVisibility(View.INVISIBLE);
    }

    public void start2(View view){
        if(binding.nameText2.getText().toString().equals("")){
            AlertDialog.Builder alert = new AlertDialog.Builder(MainMenu2.this);
            alert.setMessage("Zəhmət olmasaç ad daxil edin!");
            alert.show();
        }else{
            playerName = binding.nameText2.getText().toString();
            Intent intent2 = new Intent(MainMenu2.this, MainActivity2.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent2.putExtra("userName", playerName);
            startActivity(intent2);
            startActivity(intentInfo);
        }
    }

    public void options2(View view){
        binding.languageBtn2.setVisibility(View.VISIBLE);
        binding.difficulty2.setVisibility(View.VISIBLE);
        binding.startBtn2.setVisibility(View.INVISIBLE);
        binding.optionsBtn2.setVisibility(View.INVISIBLE);
        binding.nameText2.setVisibility(View.INVISIBLE);
    }

    public void selectLan2(View view){
        binding.languageBtn2.setVisibility(View.INVISIBLE);
        binding.difficulty2.setVisibility(View.INVISIBLE);
        binding.selectedLanAze2.setVisibility(View.VISIBLE);
        binding.selectedLanEng2.setVisibility(View.VISIBLE);
    }

    public void selectAze2(View view){
        Intent intentAze2 = getIntent();
        intentAze2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentAze2);
    }

    public void selectEng2(View view){
        Intent intentEng2 = new Intent(MainMenu2.this, MainMenu.class);
        intentEng2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentEng2);
    }

    public void setDifficulty2(View view){
        binding.languageBtn2.setVisibility(View.INVISIBLE);
        binding.difficulty2.setVisibility(View.INVISIBLE);
        binding.easyMode2.setVisibility(View.VISIBLE);
        binding.hardMode2.setVisibility(View.VISIBLE);
    }

    public void changetoEasy2(View view){
        binding.easyMode2.setVisibility(View.INVISIBLE);
        binding.hardMode2.setVisibility(View.INVISIBLE);
        binding.startBtn2.setVisibility(View.VISIBLE);
        binding.optionsBtn2.setVisibility(View.VISIBLE);
        binding.nameText2.setVisibility(View.VISIBLE);
        intentInfo = new Intent(MainMenu2.this, MainActivity2.class);
        intentInfo.putExtra("levelInfo", 0);
        intentInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public void changetoHard2(View view){
        binding.easyMode2.setVisibility(View.INVISIBLE);
        binding.hardMode2.setVisibility(View.INVISIBLE);
        binding.startBtn2.setVisibility(View.VISIBLE);
        binding.optionsBtn2.setVisibility(View.VISIBLE);
        binding.nameText2.setVisibility(View.VISIBLE);
        intentInfo = new Intent(MainMenu2.this, MainActivity2.class);
        intentInfo.putExtra("levelInfo", 1);
        intentInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.base_menu2, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.view_scores2){
            Intent intent = new Intent(MainMenu2.this, MainBase2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}