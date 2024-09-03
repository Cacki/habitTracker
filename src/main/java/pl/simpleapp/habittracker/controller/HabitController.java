package pl.simpleapp.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Habit>> getAll() {
        List<Habit> habits = service.getAll();
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getById(@PathVariable("id") int id) {
        Habit habit = service.getById(id);
        if (habit != null) {
            return ResponseEntity.ok(habit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Void> add(@RequestBody List<Habit> habits) {
        service.add(habits);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePut(@PathVariable("id") int id, @RequestBody Habit updatedHabit) {
        if (service.update(id, updatedHabit)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePath(@PathVariable("id") int id, @RequestBody Habit updatedHabit) {
        if (service.update(id, updatedHabit)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
