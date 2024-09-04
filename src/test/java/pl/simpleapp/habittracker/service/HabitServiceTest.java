package pl.simpleapp.habittracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.simpleapp.habittracker.model.Habit;
import pl.simpleapp.habittracker.repository.HabitRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class HabitServiceTest {
    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<Habit> habits = Arrays.asList(
                new Habit(1L, "Exercise", "Daily jogging", getDate()),
                new Habit(2L, "Read", "Read a book", getDate()));
        when(habitRepository.getAll()).thenReturn(habits);

        // Act
        List<Habit> result = habitService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Exercise", result.get(0).getName());
        assertEquals("Read", result.get(1).getName());
    }

    @Test
    public void testGetById_Success() {
        // Arrange
        Habit habit = new Habit(1L, "Exercise", "Daily jogging", getDate());
        when(habitRepository.getById(1)).thenReturn(habit);

        // Act
        Habit result = habitService.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Exercise", result.getName());
    }

    @Test
    public void testGetById_NotFound() {
        // Arrange
        when(habitRepository.getById(2)).thenReturn(null);

        // Act
        Habit result = habitService.getById(2);

        // Assert
        assertNull(result);
    }

    @Test
    public void testAdd() {
        // Arrange
        Habit habit = new Habit(1L, "Exercise", "Daily jogging", getDate());
        when(habitRepository.add(anyList())).thenReturn(1);

        // Act
        boolean result = habitService.add(Arrays.asList(habit));

        // Assert
        assertTrue(result);
    }

    @Test
    public void testUpdate_Success() {
        // Arrange
        Habit existingHabit = new Habit(1L, "Exercise", "Daily jogging", getDate());
        Habit updatedHabit = new Habit(1L, "Exercise", "Updated description", getDate());
        when(habitRepository.getById(1)).thenReturn(existingHabit);
        when(habitRepository.update(existingHabit)).thenReturn(true);

        // Act
        boolean result = habitService.update(1, updatedHabit);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testUpdate_NotFound() {
        // Arrange
        Habit updatedHabit = new Habit(1L, "Exercise", "Updated description", getDate());
        when(habitRepository.getById(1)).thenReturn(null);

        // Act
        boolean result = habitService.update(1, updatedHabit);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testDelete_Success() {
        // Arrange
        when(habitRepository.getById(1)).thenReturn(new Habit(1L, "Exercise", "Daily jogging", getDate()));
        when(habitRepository.delete(1)).thenReturn(true);

        // Act
        boolean result = habitService.delete(1);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testDelete_NotFound() {
        // Arrange
        when(habitRepository.getById(1)).thenReturn(null);

        // Act
        boolean result = habitService.delete(1);

        // Assert
        assertFalse(result);
    }

    private LocalDate getDate() {
        return LocalDate.parse("2024-09-01");
    }
}