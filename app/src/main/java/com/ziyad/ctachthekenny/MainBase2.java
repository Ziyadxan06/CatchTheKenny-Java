package com.ziyad.ctachthekenny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ziyad.ctachthekenny.databinding.ActivityMainBase2Binding;
import com.ziyad.ctachthekenny.databinding.ActivityMainBaseBinding;

import java.util.ArrayList;

public class MainBase2 extends AppCompatActivity {

    private ActivityMainBase2Binding binding;
    ArrayList<Scoredata> scoredataArrayList2;
    BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBase2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        scoredataArrayList2 = new ArrayList<>();

        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        baseAdapter = new BaseAdapter(scoredataArrayList2);
        binding.recyclerView2.setAdapter(baseAdapter);

        getData();
    }

    public void getData(){
        try{
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Scores", MODE_PRIVATE, null);

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM scores", null);
            int nameIx = cursor.getColumnIndex("playername");
            int scoreIx = cursor.getColumnIndex("playerscore");
            int idIx = cursor.getColumnIndex("id");

            while(cursor.moveToNext()){
                String name = cursor.getString(nameIx);
                Double score = cursor.getDouble(scoreIx);
                int id = cursor.getInt(idIx);
                Scoredata scoredata = new Scoredata(name, score, id);
                scoredataArrayList2.add(scoredata);
            }


            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.back_menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_back2){
            Intent intent = new Intent(MainBase2.this, MainMenu2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}