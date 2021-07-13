package com.example.mvvm_workout_app.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.model.WorkoutDao;

import java.util.List;

public class MainRepository {
    static MainRepository mainRepository;
    private LiveData<List<Workout>> workoutsData;
    private WorkoutDao workoutDao;

    public static MainRepository getInstance(){
        if(mainRepository==null)mainRepository = new MainRepository();
        return mainRepository;
    }

    public LiveData<List<Workout>> getWorkoutsData(Application application){
        workoutDao= DataBase.getInstance(application).workoutDao();
        workoutsData = workoutDao.readAllWorkouts();
        return workoutsData;
    }
}
