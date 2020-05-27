package com.example.github;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class homepage extends AppCompatActivity implements CallBackMe
{
    TextView name,bio;
    ImageView image;
    String url,username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        name = findViewById(R.id.name);
        image = findViewById(R.id.image);
        bio = findViewById(R.id.bio);
        Intent intent = getIntent();
        username = intent.getStringExtra("searchValue");

        url = "https://api.github.com/users/"+username;

        JsonRetriever.RetrieveFromURL(this, url, this); //First Param for Context, Last Param for Callback Function
    }

    @Override
    public void CallThis(String jsonText)
    {
        //Parse the Json here
        try
        {
            JSONObject json = new JSONObject(jsonText);

            name.setText(json.getString("login"));                              //Set Login Text
            new DownloadImageTask(image).execute(json.getString("avatar_url")); //Set Avatar Image From URL

            if (!json.getString("bio").equals("null"))
            {
                bio.setText(json.getString("bio"));
            }
            else
            {
                bio.setText("This user has no Biography.");
            }

        }

        catch (JSONException e)
        {
            bio.setText("USER NOT FOUND");
            Log.e("not found",url);
        }
    }

    public void goToFollowers(View buttonThatWasPressed)
    {
        Button pressed = (Button) buttonThatWasPressed;
        String buttonText = pressed.getText().toString();

        Intent intent = new Intent(this, follow1.class);

        intent.putExtra("situation", buttonText);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
