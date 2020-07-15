package com.example.bitternight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String FILE_NAME = "Test.csv";
    ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static EditText title;
    public static EditText description;
    private String currentDateTimeString;

    public static int newPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        buildRecyclerView();
        checkEmpty();
        setInsertButton();
        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }
    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                showMessage("Delete" + newPosition);
                removeItem(newPosition);
                saveData();
            }
        }
        ).attachToRecyclerView(mRecyclerView);

    }
    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(this);
    }
    private void insertItem(String line1,String line2) {
        generateTime();
        mExampleList.add(new ExampleItem(line1,currentDateTimeString,line2));
        mAdapter.notifyItemInserted(mExampleList.size());
    }

    public void removeItem(int position)
    {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeActivity()
    {
        Intent intent = new Intent(getApplicationContext(),AddEntry.class);
        startActivity(intent);
    }

    public void checkEmpty()
    {
        if(title == null && description == null){}
        else if(title != null && description != null){
            insertItem(title.getText().toString(),description.getText().toString());
        }
        saveData();
    }

    public void showMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }

    public void generateTime() {
        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
    }

    public void generateFile() throws IOException {
        FileOutputStream fos = null;

        String Beginning = "Title,Date/Time,Description"+"\n";
        String storagelocation = "";
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(Beginning.getBytes());
            for(int o=0;o<mExampleList.size();o++)
            {
                String lin1 = mExampleList.get(o).getLine1();
                String lin2 = mExampleList.get(o).getLine2();
                String lin3 = mExampleList.get(o).getLine3();
                String combined = lin1 + "," + lin2 +","+ lin3+"\n";
                fos.write(combined.getBytes());
            }

            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
         }
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save:
                changeActivity();
                break;
            case R.id.button_insert:
                showMessage("Button Insert Pressed");
                //showMessage(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
                try {
                    generateFile();
                } catch (IOException e) {
                    e.printStackTrace();
               }
                break;
        }
    }
}