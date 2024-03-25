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
import android.widget.Toast;

import com.ziyad.ctachthekenny.databinding.ActivityMainBinding;
import com.ziyad.ctachthekenny.databinding.ActivityMainMenuBinding;

public class MainMenu extends AppCompatActivity {

    private ActivityMainMenuBinding binding;

    String playerName;

    Intent intentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.languageBtn.setVisibility(View.INVISIBLE);
        binding.selectedLanAze.setVisibility(View.INVISIBLE);
        binding.selectedLanEng.setVisibility(View.INVISIBLE);
        binding.difficulty.setVisibility(View.INVISIBLE);
        binding.easyMode.setVisibility(View.INVISIBLE);
        binding.hardMode.setVisibility(View.INVISIBLE);

    }

    public void start(View view){
        if(binding.nameText.getText().toString().equals("")){
            AlertDialog.Builder alert = new AlertDialog.Builder(MainMenu.this);
            alert.setMessage("Please, enter a name");
            alert.show();
        }else{
            playerName = binding.nameText.getText().toString();
            Intent intent = new Intent(MainMenu.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("userName", playerName);
            startActivity(intent);
            startActivity(intentInfo);
        }

    }

    public void options(View view){
        binding.languageBtn.setVisibility(View.VISIBLE);
        binding.difficulty.setVisibility(View.VISIBLE);
        binding.startBtn.setVisibility(View.INVISIBLE);
        binding.optionsBtn.setVisibility(View.INVISIBLE);
        binding.nameText.setVisibility(View.INVISIBLE);
    }

    public void selectLan(View view){
        binding.languageBtn.setVisibility(View.INVISIBLE);
        binding.difficulty.setVisibility(View.INVISIBLE);
        binding.selectedLanAze.setVisibility(View.VISIBLE);
        binding.selectedLanEng.setVisibility(View.VISIBLE);
    }

    public void selectAze(View view){
        Intent intentAze = new Intent(MainMenu.this, MainMenu2.class);
        intentAze.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentAze);
    }

    public void selectEng(View view){
        Intent intentEng = getIntent();
        intentEng.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentEng);
    }

    public void setDifficulty(View view){
        binding.languageBtn.setVisibility(View.INVISIBLE);
        binding.difficulty.setVisibility(View.INVISIBLE);
        binding.easyMode.setVisibility(View.VISIBLE);
        binding.hardMode.setVisibility(View.VISIBLE);
    }

    public void changetoEasy(View view){
        binding.easyMode.setVisibility(View.INVISIBLE);
        binding.hardMode.setVisibility(View.INVISIBLE);
        binding.startBtn.setVisibility(View.VISIBLE);
        binding.optionsBtn.setVisibility(View.VISIBLE);
        binding.nameText.setVisibility(View.VISIBLE);
        intentInfo = new Intent(MainMenu.this, MainActivity.class);
        intentInfo.putExtra("levelInfo", 0);
        intentInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public void changetoHard(View view){
        binding.easyMode.setVisibility(View.INVISIBLE);
        binding.hardMode.setVisibility(View.INVISIBLE);
        binding.startBtn.setVisibility(View.VISIBLE);
        binding.optionsBtn.setVisibility(View.VISIBLE);
        binding.nameText.setVisibility(View.VISIBLE);
        intentInfo = new Intent(MainMenu.this, MainActivity.class);
        intentInfo.putExtra("levelInfo", 1);
        intentInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.base_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.view_scores){
            Intent intent = new Intent(MainMenu.this, MainBase.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}