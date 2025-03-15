package com.example.habit;

public class Habit {
    private Long id;
    private String habitDescription;

    public Habit() {
    }

    public Habit(String habitDescription) {
        this.habitDescription = habitDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHabit() {
        return habitDescription;
    }

    public void setHabit(String habitDescription) {
        this.habitDescription = habitDescription;
    }
}
