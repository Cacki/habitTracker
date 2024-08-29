package pl.simpleapp.habittracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HabitController {

    @Autowired
    HabitRepository habitRepository;

    @GetMapping("/test")
    public int test() {
        return 1;
    }

    @GetMapping("/habits")
    public List<Habit> getAll() {
        return habitRepository.getAll();
    }

}
