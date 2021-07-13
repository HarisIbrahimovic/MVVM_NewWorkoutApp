package com.example.mvvm_workout_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_workout")
public class Workout {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Workout(String name) {

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
