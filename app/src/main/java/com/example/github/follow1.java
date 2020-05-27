package com.example.github;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class follow1 extends AppCompatActivity implements CallBackMe
{
    RecyclerView recyclerView;
    String url,username, situation;
    TextView label;

    List<CustomData> myDataSet = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow1);

        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager LinearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayout);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        situation = intent.getStringExtra("situation");

        label = findViewById(R.id.label);
        label.setText(username + " / " + situation);

        if (situation.equals("followers"))
            url = "https://api.github.com/users/"+username+"/followers";
        else
            url = "https://api.github.com/users/"+username+"/following";

        JsonRetriever.RetrieveFromURL(this, url, this); //First Param for Context, Last Param for Callback Function

        RecyclerAdapter myAdapter = new RecyclerAdapter(myDataSet);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void CallThis(String jsonText)
    {
        try
        {
            JSONArray jsonArray = new JSONArray(jsonText);

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject  jsonObject = jsonArray.getJSONObject(i);
                jsonObject.getString("login");
                Log.e("lo",jsonObject.getString("login"));
                CustomData c1 = new CustomData(jsonObject.getString("login"),jsonObject.getString("avatar_url"));
                myDataSet.add(c1);
            }

           RecyclerAdapter r1= new RecyclerAdapter(myDataSet);
           recyclerView.setAdapter(r1);
        }

        catch (JSONException e)
        {
            Log.e("not found",url);
        }
    }
}

