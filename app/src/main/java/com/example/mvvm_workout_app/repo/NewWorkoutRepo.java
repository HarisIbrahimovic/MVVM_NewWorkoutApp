package com.example.mvvm_workout_app.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.ExerciseDao;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.model.WorkoutDao;

import java.util.List;

public class NewWorkoutRepo {
    static NewWorkoutRepo newWorkoutRepo;
    private WorkoutDao workoutDao;

    public static NewWorkoutRepo getInstance(){
        if(newWorkoutRepo==null)newWorkoutRepo = new NewWorkoutRepo();
        return newWorkoutRepo;
    }

    public void insertWorkout(Application application, Workout workout) {
        workoutDao= DataBase.getInstance(application).workoutDao();
        new InsertNoteAsyncTask(workoutDao).execute(workout);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Workout, Void, Void> {
        private WorkoutDao workoutDao;
        private InsertNoteAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }
        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.addWorkout(workouts[0]);
            return null;
        }
    }

}
