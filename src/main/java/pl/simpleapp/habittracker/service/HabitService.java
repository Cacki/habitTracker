package pl.simpleapp.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.simpleapp.habittracker.model.Habit;
import pl.simpleapp.habittracker.repository.HabitRepository;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository repository;

    @Autowired
    public HabitService(HabitRepository repository) {
        this.repository = repository;
    }

    public List<Habit> getAll() {
        return repository.getAll();
    }

    public Habit getById(int id) {
        return repository.getById(id);
    }

    public boolean add(List<Habit> habits) {
        if (habits == null || habits.isEmpty())
            throw new IllegalArgumentException("Habits list must not be null or empty");

        return repository.add(habits) > 0;
    }

    public boolean update(int id, Habit updatedHabit) {
        Habit habit = repository.getById(id);
        if (habit == null)
            return false;

        updateValues(habit, updatedHabit);
        return repository.update(habit);
    }

    public boolean delete(int id) {
        Habit habit = repository.getById(id);
        if (habit == null)
            return false;

        return repository.delete(id);
    }

    private void updateValues(Habit habit, Habit updatedHabit) {
        if (updatedHabit.getName() != null && !updatedHabit.getName().equals(habit.getName()))
            habit.setName(updatedHabit.getName());

        if (updatedHabit.getDescription() != null && !updatedHabit.getDescription().equals(habit.getDescription()))
            habit.setDescription(updatedHabit.getDescription());

        if (updatedHabit.getStartDate() != null && !updatedHabit.getStartDate().equals(habit.getStartDate()))
            habit.setStartDate(updatedHabit.getStartDate());
    }

}
