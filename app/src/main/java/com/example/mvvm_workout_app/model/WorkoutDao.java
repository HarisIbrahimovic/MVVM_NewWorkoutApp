package com.example.mvvm_workout_app.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWorkout(Workout workout);

    @Query("SELECT * FROM table_workout")
    LiveData<List<Workout>> readAllWorkouts();

    @Query("DELETE FROM table_workout WHERE id = :input")
    void deleteWorkout(int input);

}
