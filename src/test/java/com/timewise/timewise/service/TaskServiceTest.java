package com.timewise.timewise.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.timewise.timewise.model.Task;
import com.timewise.timewise.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService; // El servicio que vamos a probar

    @MockBean
    private TaskRepository taskRepository; // Simulamos el repositorio

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllTasks() {
        // Configuramos el comportamiento simulado del repositorio
        Task task1 = new Task();
        task1.setTitle("Tarea 1");
        task1.setPriority(1);

        Task task2 = new Task();
        task2.setTitle("Tarea 2");
        task2.setPriority(2);

        when(taskRepository.findAllByOrderByPriorityAsc()).thenReturn(Arrays.asList(task1, task2));

        // Ejecutamos el método a probar
        List<Task> tasks = taskService.getAllTasks();

        // Verificamos los resultados
        assertEquals(2, tasks.size());
        assertEquals("Tarea 1", tasks.get(0).getTitle());
        assertEquals(1, tasks.get(0).getPriority());
    }

    @Test
    void testGetTaskById() {
        // Configuramos el comportamiento del mock
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Tarea 1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Llamamos al servicio
        Task foundTask = taskService.getTaskById(1L);

        // Verificamos que la tarea sea la correcta
        assertNotNull(foundTask);
        assertEquals(1L, foundTask.getId());
        assertEquals("Tarea 1", foundTask.getTitle());
    }

    @Test
    void testUpdatePriority() {
        // Configuramos el comportamiento del mock
        Task task = new Task();
        task.setId(1L);
        task.setPriority(2);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Llamamos al servicio para actualizar la prioridad
        Task updatedTask = taskService.updatePriority(1L, 1);

        // Verificamos el resultado
        assertEquals(1, updatedTask.getPriority());
        verify(taskRepository).save(task); // Verificamos que el repositorio guardó la tarea
    }

    @Test
    void testDeleteTask() {
        // Configuramos el comportamiento del mock
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Ejecutamos el método de eliminación
        taskService.deleteTask(1L);

        // Verificamos que el repositorio haya eliminado la tarea
        verify(taskRepository).delete(task);
    }
}