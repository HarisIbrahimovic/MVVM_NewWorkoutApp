package com.example.mvvm_workout_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvm_workout_app.Adapters.MyAdapterExcs;
import com.example.mvvm_workout_app.R;
import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.viewmodels.DetailsViewModel;
import com.example.mvvm_workout_app.viewmodels.NewExerciseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements MyAdapterExcs.TouchListener{
    private RecyclerView recyclerView;
    private MyAdapterExcs myAdapterExcs;
    private DetailsViewModel viewModel;
    private FloatingActionButton addExercise;
    private FloatingActionButton deleteWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        viewModel.init(getApplication(),getIntent().getIntExtra("workoutId",0));
        setUpStuff();
        observe();
        onClicks();
    }

    private void onClicks() {
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewExerciseActivity.class);
                intent.putExtra("workoutId",getIntent().getIntExtra("workoutId",0));
                startActivity(intent);
            }
        });

        deleteWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteWorkout(getApplication(),getIntent().getIntExtra("workoutId",0));
                Toast.makeText(getApplicationContext(),"Workout removed.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void observe() {
        viewModel.getExerciseData().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                myAdapterExcs.setExercises(exercises);
            }
        });
    }

    private void setUpStuff() {
        addExercise = findViewById(R.id.detailsAdd);
        deleteWorkout = findViewById(R.id.deleteW);
        recyclerView = findViewById(R.id.exersiceListRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myAdapterExcs = new MyAdapterExcs(getApplicationContext(),this);
        recyclerView.setAdapter(myAdapterExcs);
    }

    @Override
    public void onNoteClick(int position) {
        viewModel.deleteExercise(getApplication(),viewModel.getExerciseData().getValue().get(position).getId());
        Toast.makeText(getApplicationContext(),"Removed.",Toast.LENGTH_SHORT).show();
    }
}