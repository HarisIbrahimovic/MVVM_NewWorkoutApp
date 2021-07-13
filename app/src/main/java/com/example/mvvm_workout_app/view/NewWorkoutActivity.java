package com.example.mvvm_workout_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvm_workout_app.Adapters.MyAdapterExcs;
import com.example.mvvm_workout_app.R;
import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.viewmodels.NewWorkoutViewModel;

import java.util.List;

public class NewWorkoutActivity extends AppCompatActivity{

    private EditText workoutName;
    private Button button;
    private NewWorkoutViewModel viewModel;
    private int created;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout);
        viewModel = ViewModelProviders.of(this).get(NewWorkoutViewModel.class);
        viewModel.init();
        setUpStuff();
        observe();
        onClick();
    }

    private void onClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkStatus();
                viewModel.checkName(workoutName.getText().toString().trim());
            }
        });
    }

    private void checkStatus() {
        if(created==3){
            openNewExActivity();
        }
    }

    private void openNewExActivity() {
        Intent intent = new Intent(getApplicationContext(),NewExerciseActivity.class);
        intent.putExtra("lastNum",1);
        startActivity(intent);
    }

    private void observe() {
        viewModel.getCheckData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                created=integer;
                checkStatusOB();
            }
        });
    }

    private void checkStatusOB() {
        if(created==1){
            viewModel.insertWorkout(workoutName.getText().toString().trim(),getApplication());
            viewModel.setCheckWorkout(3);
            Toast.makeText(getApplicationContext(),"Workout created",Toast.LENGTH_SHORT).show();
            openNewExActivity();
        }else if(created==2){
            Toast.makeText(getApplicationContext(),"Please fill in the name",Toast.LENGTH_SHORT).show();
            viewModel.setCheckWorkout(0);
        }
    }

    private void setUpStuff() {
        workoutName = findViewById(R.id.workoutName);
        button = findViewById(R.id.newExerciseButton);
    }
}