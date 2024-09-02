package pl.simpleapp.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.simpleapp.habittracker.model.Habit;
import pl.simpleapp.habittracker.service.HabitService;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

    private final HabitService service;

    @Autowired
    public HabitController(HabitService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Habit> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Habit getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public int add(@RequestBody List<Habit> habits) {
        return service.add(habits);
    }

    @PutMapping("/{id}")
    public int updatePut(@PathVariable("id") int id, @RequestBody Habit updatedHabit) {
        return service.update(1, updatedHabit);
    }

    @PatchMapping("/{id}")
    public int updatePath(@PathVariable("id") int id, @RequestBody Habit updatedHabit) {
        return service.update(id, updatedHabit);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

}
