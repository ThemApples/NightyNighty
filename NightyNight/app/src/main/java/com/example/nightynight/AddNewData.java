package com.example.nightynight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddNewData extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button add;
    Button close;

    Spinner spin;

    EditText title;
    EditText desc;

    String titl;
    String descr;
    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_data);

        spin = findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.mood,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        add = (Button) findViewById(R.id.add_b);
        add.setOnClickListener(this);

        close = (Button) findViewById(R.id.del_c);
        close.setOnClickListener(this);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.Descr);

    }

    private void writingData()
    {
        //Getting Time/Date and userInput
        String cDTS = java.text.DateFormat.getDateTimeInstance().format(new Date());
        titl = title.getText().toString();
        descr = desc.getText().toString();

        MainActivity.information = new Info();
        MainActivity.information.setDate(cDTS);
        MainActivity.information.setTitle(titl);
        MainActivity.information.setDesc(descr);
        MainActivity.information.setMood(mood);

        MainActivity.user_database.add(MainActivity.information);
        MainActivity.PassOver.add(MainActivity.information);
    }

    private void goback()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.add_b:
            writingData();
            goback();
            break;

            case R.id.del_c:
            goback();
            break;
        }

    }

    private void show(String display)
    {
        Toast.makeText(getApplicationContext(),display,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        mood = text;
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
