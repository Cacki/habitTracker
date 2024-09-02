package pl.simpleapp.habittracker.model;

import lombok.*;

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
