package pl.simpleapp.habittracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HabitRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Habit> getAll() {
        return jdbcTemplate.query("SELECT * FROM habit",
                BeanPropertyRowMapper.newInstance(Habit.class));
    }

}
