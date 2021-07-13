package com.example.mvvm_workout_app.model;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addExercise(Exercise exercise);

    @Query("SELECT * FROM table_exercise WHERE workoutId=:inputId ")
    LiveData<List<Exercise>> getExercise(int inputId);

    @Query("DELETE FROM table_exercise WHERE id = :input")
    void deleteExercise(int input);

}
