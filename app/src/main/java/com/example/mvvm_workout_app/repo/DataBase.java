package com.example.mvvm_workout_app.repo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvm_workout_app.model.Exercise;
import com.example.mvvm_workout_app.model.ExerciseDao;
import com.example.mvvm_workout_app.model.Workout;
import com.example.mvvm_workout_app.model.WorkoutDao;

@Database(entities = {Workout.class, Exercise.class}, version = 1, exportSchema = false)
abstract class DataBase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    public abstract WorkoutDao workoutDao();
    private static DataBase instance;

    public static synchronized DataBase getInstance(Context context){
        if(instance == null){
            instance =  Room.databaseBuilder(context.getApplicationContext(), DataBase.class , "Database_name")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
