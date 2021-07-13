package com.example.mvvm_workout_app.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.repo.NewExerciseRepository;

import java.util.List;

public class NewExerciseViewModel extends ViewModel {
    private NewExerciseRepository repository;
    private LiveData<List<Workout>> workoutData;

    public void init(Application application){
        repository = NewExerciseRepository.getInstance();
        workoutData = repository.getWorkoutsData(application);
    }


    public LiveData<List<Workout>> getWorkoutData() {
        return workoutData;
    }

    public void insertExercise(Application application, Exercise exercise){
        repository.insertExercise(application,exercise);
    }
}
