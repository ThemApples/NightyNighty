package com.example.bitternight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEntry extends AppCompatActivity implements View.OnClickListener {

    private Button addButton;
    private EditText title;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        title = findViewById(R.id.Title);
        description = findViewById(R.id.Desc);
        addButton = findViewById(R.id.add);

        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.add:
                    MainActivity.title = title;
                    MainActivity.description = description;
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    break;
            }
    }
}