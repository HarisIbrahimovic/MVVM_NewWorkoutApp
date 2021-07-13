package com.example.mvvm_workout_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_exercise")
public class Exercise {
    @PrimaryKey(autoGenerate = true)//Better with random generated string
    private int id;
    private int workoutId;
    private String name;
    private int set;
    private String weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Exercise(int workoutId, String name, int set, String weight) {
        this.workoutId = workoutId;
        this.name = name;
        this.set = set;
        this.weight = weight;
    }
}
