package pl.simpleapp.habittracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.simpleapp.habittracker.model.Habit;

import java.util.List;

@Repository
public class HabitRepository {
    private final String SELECT_ALL = "SELECT * FROM habits";
    private final String SELECT_BY_ID = "SELECT * FROM habits WHERE id = ?";
    private final String INSERT = "INSERT INTO habits (name, description, start_date) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE habits SET name = ?, description = ?, start_date = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM habits WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HabitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Habit> getAll() {
        return jdbcTemplate.query(SELECT_ALL,
                BeanPropertyRowMapper.newInstance(Habit.class));
    }

    public Habit getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID,
                BeanPropertyRowMapper.newInstance(Habit.class), id);
    }

    public int add(List<Habit> habits) {
        int totalInserted = 0;
        for (Habit habit : habits) {
            int rowsAffected = jdbcTemplate.update(INSERT, habit.getName(), habit.getDescription(), habit.getStartDate());
            if (rowsAffected > 0) {
                totalInserted++;
            }
        }
        return totalInserted;
    }

    public boolean update(Habit habit) {
        int rowsAffected = jdbcTemplate.update(UPDATE, habit.getName(), habit.getDescription(), habit.getStartDate(), habit.getId());
        return rowsAffected > 0;
    }

    public boolean delete(int id) {
        int rowsAffected = jdbcTemplate.update(DELETE, id);
        return rowsAffected > 0;
    }
}
