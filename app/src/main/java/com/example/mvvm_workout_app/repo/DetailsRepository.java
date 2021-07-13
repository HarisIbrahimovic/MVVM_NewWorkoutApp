package com.example.mvvm_workout_app.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.ExerciseDao;
import com.example.mvvm_workout_app.model.WorkoutDao;

import java.util.List;

public class DetailsRepository {

    static DetailsRepository instance;
    private WorkoutDao workoutDao;
    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> exerciseData;

    public static DetailsRepository getInstance(){
        if(instance==null)instance = new DetailsRepository();
        return instance;
    }

    public void deleteWorkout(Application application, int id){
        workoutDao = DataBase.getInstance(application).workoutDao();
        new asyncDeleteW(workoutDao).execute(id);
    }

    public void deleteExercise(Application application, int id){
        exerciseDao = DataBase.getInstance(application).exerciseDao();
        new asyncDeleteE(exerciseDao).execute(id);
    }

    public LiveData<List<Exercise>> getExerciseData(Application application, int id){
        exerciseDao = DataBase.getInstance(application).exerciseDao();
        exerciseData = exerciseDao.getExercise(id);
        return exerciseData;
    }

    public static class asyncDeleteE extends AsyncTask<Integer , Void ,Void> {
        private ExerciseDao exerciseDao;
        private asyncDeleteE(ExerciseDao exerciseDao){
            this.exerciseDao=exerciseDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            exerciseDao.deleteExercise(integers[0]);
            return null;
        }
    }

    public static class asyncDeleteW extends AsyncTask<Integer , Void ,Void> {
        private WorkoutDao workoutDao;
        private asyncDeleteW(WorkoutDao workoutDao){
            this.workoutDao=workoutDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            workoutDao.deleteWorkout(integers[0]);
            return null;
        }
    }

}
