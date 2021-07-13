package com.example.mvvm_workout_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvm_workout_app.R;
import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.viewmodels.NewExerciseViewModel;

import java.util.List;

public class NewExerciseActivity extends AppCompatActivity {

    private NewExerciseViewModel viewModel;
    private int workoutId;
    private Button addButton;
    private EditText name;
    private EditText sets;
    private EditText weight;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        viewModel = ViewModelProviders.of(this).get(NewExerciseViewModel.class);
        viewModel.init(getApplication());
        setUpStuff();
        observe();
        onClick();
    }

    private void setUpStuff() {
        addButton = findViewById(R.id.addEx);
        name = findViewById(R.id.exName);
        sets = findViewById(R.id.exSets);
        weight = findViewById(R.id.exWeight);
    }

    private void onClick() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString().trim())||TextUtils.isEmpty(sets.getText().toString().trim())||TextUtils.isEmpty(weight.getText().toString().trim()))
                    return;
                setExercise();
                viewModel.insertExercise(getApplication(), exercise);
                Toast.makeText(getApplicationContext(), "Added.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setExercise() {
        String Name = name.getText().toString();
        String Weight = weight.getText().toString().trim();
        int Sets = Integer.parseInt(sets.getText().toString().trim());
        if(getIntent().hasExtra("lastNum")) exercise= new Exercise(workoutId,Name,Sets,Weight);
        else exercise = new Exercise(getIntent().getIntExtra("workoutId",0),Name,Sets,Weight);
    }


    private void observe() {
        viewModel.getWorkoutData().observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                workoutId = workouts.get(workouts.size()-1).getId();
            }
        });
    }
}