package com.example.github;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView text;
    public ImageView image;
    Context myContext;

    public CustomViewHolder(@NonNull View customLayoutView)
    {
        super(customLayoutView);

        text = customLayoutView.findViewById(R.id.template_text);
        image = customLayoutView.findViewById(R.id.template_image);
        itemView.setOnClickListener(this);
    }

    public void bindData(final CustomData data)
    {
        text.setText(data.text);
        new DownloadImageTask(image).execute(data.imageID);
    }

    @Override
    public void onClick(View v)
    {
        Intent myIntent = new Intent(v.getContext(), homepage.class);
        myIntent.putExtra("searchValue", text.getText().toString());
        v.getContext().startActivity(myIntent);
    }
}
