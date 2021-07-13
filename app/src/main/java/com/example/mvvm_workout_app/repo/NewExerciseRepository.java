package com.example.mvvm_workout_app.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.ExerciseDao;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.model.WorkoutDao;

import java.util.List;

public class NewExerciseRepository {
    static NewExerciseRepository repository;
    private WorkoutDao workoutDao;
    private ExerciseDao exerciseDao;
    private LiveData<List<Workout>> workoutsData;

    public static NewExerciseRepository getInstance(){
        if(repository==null)repository=new NewExerciseRepository();
        return repository;
    }

    public void insertExercise(Application application, Exercise exercise){
        exerciseDao = DataBase.getInstance(application).exerciseDao();
        new InsertExercise(exerciseDao).execute(exercise);
    }

    public LiveData<List<Workout>> getWorkoutsData(Application application){
        workoutDao= DataBase.getInstance(application).workoutDao();
        workoutsData = workoutDao.readAllWorkouts();
        return workoutsData;
    }

    private static class InsertExercise extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDao exerciseDao;
        private InsertExercise(ExerciseDao exerciseDao) {
            this.exerciseDao = exerciseDao;
        }
        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDao.addExercise(exercises[0]);
            return null;
        }
    }
}
