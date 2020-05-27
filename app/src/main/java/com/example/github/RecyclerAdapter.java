package com.example.github;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder>
{
    private List<CustomData> data;

    public RecyclerAdapter (@NonNull List<CustomData> data)
    {
        this.data = data;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final View customLayout = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        CustomViewHolder viewHolder = new CustomViewHolder(customLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return R.layout.activity_template;
    }
}
