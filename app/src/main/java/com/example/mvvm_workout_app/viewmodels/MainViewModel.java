package com.example.mvvm_workout_app.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.repo.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MainRepository repository;
    private LiveData<List<Workout>> workoutData;

    public void init(Application application){
        repository = MainRepository.getInstance();
        workoutData = repository.getWorkoutsData(application);
    }

    public LiveData<List<Workout>> getWorkoutData( ) {
        return workoutData;
    }
}
