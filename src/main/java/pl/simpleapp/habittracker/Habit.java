package pl.simpleapp.habittracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
}
