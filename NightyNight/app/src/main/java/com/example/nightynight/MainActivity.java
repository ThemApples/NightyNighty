package com.example.nightynight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.SharedPreferencesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {

    private FloatingActionButton aButton;

    private RecyclerView mRecyclerView;
    private exampleAdapter mAdapter;
    private RecyclerView.LayoutManager rlm;

    private ArrayList<userEntry> a;
    private ArrayList<userEntry> loader;
    public static ArrayList<Info> user_database = new ArrayList<Info>();
    public static Info information = new Info();

    public static final String Shared_PREFS = "sharedPrefs";
    public static final ArrayList<Info> PassOver = new ArrayList<>();
    public static String bottomContent;

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //text = findViewById(R.id.Inside);
        //text.setText("hello");
        //loaddata();
        loader = new ArrayList<>();
        setup();

        a = new ArrayList<>();
        for(int i = 0; i< user_database.size(); i++)
        {
            if(user_database.get(i).getMood().equals("Sad"))
            {
                a.add(new userEntry(R.drawable.ic_sad,user_database.get(i).getTitle(),user_database.get(i).getDate()));
                saveData();
            }
            else if(user_database.get(i).getMood().equals("Angry"))
            {
                a.add(new userEntry(R.drawable.ic_angry,user_database.get(i).getTitle(),user_database.get(i).getDate()));
                saveData();
            }
            else if(user_database.get(i).getMood().equals("Happy"))
            {
                a.add(new userEntry(R.drawable.ic_happy,user_database.get(i).getTitle(),user_database.get(i).getDate()));
                saveData();
            }
            else if(user_database.get(i).getMood().equals("Calm"))
            {
                a.add(new userEntry(R.drawable.ic_calm,user_database.get(i).getTitle(),user_database.get(i).getDate()));
                saveData();
            }
            else{
                a.add(new userEntry(R.drawable.ic_android,user_database.get(i).getTitle(),user_database.get(i).getDate()));
                saveData();
            }
        }


        aButton = findViewById(R.id.float_b);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActvity();
            }
        });

        mRecyclerView = findViewById(R.id.recylerView);
        mRecyclerView.setHasFixedSize(true);
        rlm = new LinearLayoutManager(this);
        mAdapter = new exampleAdapter(a);

        mRecyclerView.setLayoutManager(rlm);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new exampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String t = user_database.get(position).getTitle();
                String m = user_database.get(position).getMood();
                String d = user_database.get(position).getDate();
                String dd = user_database.get(position).getDesc();
                //show(t + "\n" + d +" \n" + m +"\n"+ dd);
                bottomSheetSetup(t,m,d,dd);

                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(),"exampleBottomSheet");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

    }

    private void bottomSheetSetup(String title,String mood,String date,String description)
    {
        bottomContent = title.toUpperCase() + "\n " + date.toUpperCase() +" \n" + mood.toUpperCase() +"\n\n"+ description;
    }

    private void openAddActvity() {
        Intent intent = new Intent(this, AddNewData.class);
        startActivity(intent);

    }

    public void removeItem(int position)
    {
        a.remove(position);
        user_database.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void saveData()
    {
        SharedPreferences sp = getSharedPreferences(Shared_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        Gson gson = new Gson();
        String json = gson.toJson(PassOver);
        ed.putString("Backup data",json);
        ed.apply();

    }

    public void loaddata()
    {
        SharedPreferences sp = getSharedPreferences(Shared_PREFS,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("Backup data",null);
        Type type = new TypeToken<ArrayList<Info>>() {}.getType();
        loader = gson.fromJson(json,type);
    }

    public void setup()
    {
        for(int i = 0; i < loader.size(); i++)
        {
            user_database.add(loader.get(i));
        }

        show("loader Done");
    }

    private void show(String display)
    {
        Toast.makeText(getApplicationContext(),display,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClicked(String text) {

    }
}
