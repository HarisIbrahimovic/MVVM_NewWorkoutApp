package com.example.mvvm_workout_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvm_workout_app.Adapters.MyAdapterWorkouts;
import com.example.mvvm_workout_app.R;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.viewmodels.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapterWorkouts.TouchListener{
    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private MyAdapterWorkouts myAdapterWorkouts;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.init(getApplication());
        setUpRecView();
        observe();
        onClicks();
    }

    private void onClicks() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewWorkoutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void observe() {
        viewModel.getWorkoutData().observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                myAdapterWorkouts.setWorkouts(workouts);
            }
        });
    }

    private void setUpRecView() {
        myAdapterWorkouts = new MyAdapterWorkouts(getApplicationContext(),MainActivity.this);
        recyclerView = findViewById(R.id.myWorkoutsRecView);
        recyclerView.setAdapter(myAdapterWorkouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        floatingActionButton = findViewById(R.id.addWorkoutButton);
    }

    @Override
    public void onNoteClick(int position) {
        Workout workout = viewModel.getWorkoutData().getValue().get(position);
        Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
        intent.putExtra("workoutId",workout.getId());
        startActivity(intent);
    }

}