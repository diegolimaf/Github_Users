package com.example.github;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    TextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
    }
    public void goToResult(View view)
    {
        if (!search.getText().toString().equals(""))
        {
            String searchValue = search.getText().toString();
            Intent intent = new Intent(this, homepage.class);
            intent.putExtra("searchValue",searchValue);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Enter a value!", Toast.LENGTH_SHORT).show();
        }
    }
}
