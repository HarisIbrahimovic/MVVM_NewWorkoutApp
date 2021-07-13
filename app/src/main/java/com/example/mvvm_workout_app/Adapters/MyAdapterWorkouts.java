package com.example.mvvm_workout_app.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvm_workout_app.R;
import com.example.mvvm_workout_app.model.Workout;
import java.util.List;

public class MyAdapterWorkouts extends RecyclerView.Adapter<MyAdapterWorkouts.MyViewHolder> {

    private Context context;
    private List<Workout> workouts;
    private TouchListener touchListener;

    public MyAdapterWorkouts(Context context, TouchListener touchListener) {
        this.context = context;
        this.touchListener = touchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.workout_item,parent,false);
        return new MyViewHolder(view,touchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Workout w = workouts.get(position);
        holder.name.setText(w.getName());
    }

    @Override
    public int getItemCount() {
        if( workouts==null)return 0;
        else return workouts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name = itemView.findViewById(R.id.wNameRecView);
        public MyViewHolder(@NonNull View itemView,TouchListener TouchListener) {
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

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged();
    }
}