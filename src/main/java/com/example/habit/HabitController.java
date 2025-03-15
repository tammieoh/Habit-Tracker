package com.example.habit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("habit")
@RestController
public class HabitController {

    private final HabitApplication habitApplication;
    private HashMap<Long, Habit> habits = new HashMap();
    private final AtomicLong idCounter = new AtomicLong(1);

    public HabitController(HabitApplication habitApplication) {
        this.habitApplication = habitApplication;
    }

    // get a list of habits
    @GetMapping()
    public Map<Long, Habit> getAllHabits() {
        return this.habits;
    }

    @GetMapping("/{id}")
    public Habit getHabitById(@PathVariable Long id) {
        return this.habits.get(id);
    }

    // get key from value
    public Long getKeyFromValue(String habit) {
        for (Map.Entry<Long, Habit> map : this.habits.entrySet()) {
            if (map.getValue().equals(habit)) {
                return map.getKey();
            }
        }
        return -1L;
    }

    // create a new habit
    @PostMapping()
    public Habit createHabit(@RequestBody Habit habit) {
        if (!this.habits.containsValue(habit)) {
            habit.setId(idCounter.getAndIncrement());
            this.habits.put(habit.getId(), habit);
            return habit;
        }
        return null;
    }

    // delete a habit
    @DeleteMapping("/{id}")
    public String deleteHabbit(@PathVariable Long id) {
        if (this.habits.get(id) != null) {
            this.habits.remove(id);
            return "Successfully deleted: " + this.habits.get(id).getHabit();
        }
        return "Could not find habit.";
    }

    // update a habit
    @PutMapping("/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Habit updateHabit) {
        // TODO: process PUT request
        if (this.habits.get(id) != null) {
            Habit h = this.habits.get(id);
            h.setHabit(updateHabit.getHabit());
            this.habits.put(id, h);
        }
        return "Successfully updated: " + updateHabit.getHabit();
    }
}
