package pl.simpleapp.habittracker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.simpleapp.habittracker.model.Habit;
import pl.simpleapp.habittracker.service.HabitService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HabitController.class)
class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitService habitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        // Arrange
        List<Habit> habits = Arrays.asList(
                new Habit(1L, "Exercise", "Daily jogging", getDate()),
                new Habit(2L, "Read", "Read a book", getDate())
        );
        when(habitService.getAll()).thenReturn(habits);

        // Act and Assert
        mockMvc.perform(get("/habits/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Exercise"))
                .andExpect(jsonPath("$[1].name").value("Read"));
    }

    @Test
    public void testGetById_Success() throws Exception {
        // Arrange
        Habit habit = new Habit(1L, "Exercise", "Daily jogging", getDate());
        when(habitService.getById(1)).thenReturn(habit);

        // Act and Assert
        mockMvc.perform(get("/habits/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Exercise"));
    }

    @Test
    public void testGetById_NotFound() throws Exception {
        // Arrange
        when(habitService.getById(2)).thenReturn(null);

        // Act and Assert
        mockMvc.perform(get("/habits/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAdd() throws Exception {
        // Arrange
        Habit habit = new Habit(1L, "Exercise", "Daily jogging", getDate());
        when(habitService.add(anyList())).thenReturn(true);

        // Act and Assert
        mockMvc.perform(post("/habits/")
                        .contentType("application/json")
                        .content("[{\"name\": \"Exercise\", \"description\": \"Daily jogging\", \"startDate\": \"2023-09-01\"}]"))
                .andExpect(status().is(201));
    }

    private LocalDate getDate() {
        return LocalDate.parse("2024-09-01");
    }
}