package com.example.mvvm_workout_app.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.repo.NewWorkoutRepo;

import java.util.List;

public class NewWorkoutViewModel extends ViewModel {

    private NewWorkoutRepo repository;
    private MutableLiveData<Integer> checkWorkout = new MutableLiveData<>() ;


    public void init(){
        repository = NewWorkoutRepo.getInstance();
        checkWorkout.postValue(0);
        checkWorkout.setValue(0);
    }

    public void setCheckWorkout(int num){
        checkWorkout.postValue(num);
        checkWorkout.setValue(num);
    }

    public LiveData<Integer> getCheckData(){
        return checkWorkout;
    }
    public void insertWorkout(String name, Application application){
        repository.insertWorkout(application,new Workout(name));
    }

    public void checkName(String name) {
        if(checkWorkout.getValue()==3)return;
        if(TextUtils.isEmpty(name)) {
            setCheckWorkout(2);
            return;
        }
        checkWorkout.setValue(1);
    }
}
