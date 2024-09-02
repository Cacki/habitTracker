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
        //@TODO validate
        return repository.getById(id);
    }

    public int add(List<Habit> habits) {
        //@TODO validate
        return repository.add(habits);
    }

    public int update(int id, Habit updatedHabit) {
        //@TODO validate
        Habit habit = repository.getById(id);

        if (habit != null) {
            updateValues(habit, updatedHabit);
            return repository.update(habit);
        } else {
            return -1;
        }

    }

    public int delete(int id) {
        //@TODO validate
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
