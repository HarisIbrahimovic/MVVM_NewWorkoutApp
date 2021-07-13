package com.example.mvvm_workout_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvvm_workout_app.R;
import com.example.mvvm_workout_app.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterExcs extends RecyclerView.Adapter<MyAdapterExcs.MyViewHolder> {
    private Context context;
    private List<Exercise> exercises;
    private TouchListener touchListener;

    public MyAdapterExcs(Context context, TouchListener touchListener) {
        this.context = context;
        this.touchListener = touchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exc_item,parent,false);
        return new MyViewHolder(view,touchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exercise e = exercises.get(position);
        holder.exName.setText(e.getName());
        holder.exReps.setText(String.valueOf(e.getSet()));
        holder.exSets.setText(e.getWeight());
    }

    @Override
    public int getItemCount() {
        if(exercises==null)return 0;
        return exercises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView exName = itemView.findViewById(R.id.exNameItem);
        TextView exReps = itemView.findViewById(R.id.exRepsItem);
        TextView exSets = itemView.findViewById(R.id.exSetsItem);
        public MyViewHolder(@NonNull View itemView, TouchListener TouchListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            touchListener = TouchListener;
        }

        @Override
        public void onClick(View v) {
            touchListener.onNoteClick(getAdapterPosition());

        }
    }

    public interface TouchListener{
        void onNoteClick(int position);
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }
}
