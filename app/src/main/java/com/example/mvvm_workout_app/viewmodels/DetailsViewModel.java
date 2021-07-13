package com.example.mvvm_workout_app.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.repo.DetailsRepository;

import java.util.List;

public class DetailsViewModel extends ViewModel {

    private LiveData<List<Exercise>> exerciseData;
    private DetailsRepository repository;

    public void init(Application application, int id){
        if(exerciseData!=null)return;
        repository = DetailsRepository.getInstance();
        exerciseData = repository.getExerciseData(application,id);
    }

    public LiveData<List<Exercise>> getExerciseData() {
        return exerciseData;
    }

    public void deleteExercise(Application application, int id){
        repository.deleteExercise(application,id);
    }

    public void deleteWorkout(Application application,int id){
        repository.deleteWorkout(application,id);
    }

}
